package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.entity.FileDO;
import top.warmj.entity.FileBoxDO;
import top.warmj.entity.RelationDO;
import top.warmj.dto.ResultDTO;
import top.warmj.service.FileBoxService;
import top.warmj.service.FileService;
import top.warmj.service.RelationService;
import top.warmj.util.FileBoxUtils;

import java.util.*;

@RestController
@RequestMapping("/fileBox")
@SuppressWarnings("unchecked") // 消除强转警告
public class FileBoxController {
    @Autowired
    FileBoxService fileBoxService;

    @Autowired
    RelationService relationService;

    @Autowired
    FileService fileService;

    private static final String FILEPATH = "D:/upload/"; // 存储路径

    /**
     * 获取所有文档集
     *
     * @return
     */
    @GetMapping({"/", ""})
    public ResultDTO<List<FileBoxDO>> listAllFileBoxes() {
        return new ResultDTO<>(fileBoxService.listAllFileBoxes());
    }

    /**
     * 获取所有文档集
     * 加上分页参数
     *
     * @return
     */
    @GetMapping({"/parameter"})
    public ResultDTO<List<FileBoxDO>> listAllFileBoxesByLimit(@RequestParam int page, @RequestParam int limit) {
        List<FileBoxDO> list = fileBoxService.listAllFileBoxesByLimit((page - 1) * limit, limit);
        int count = fileBoxService.listAllFileBoxes().size();
        return new ResultDTO<>(list, count);
    }

    /**
     * 通过idList获取文档集
     * @return
     */
    @GetMapping("/id")
    public ResultDTO<List<FileBoxDO>> listFileBoxesByIdList(@RequestBody Map<String, Object> map) {
        // 从map中获取id
        ArrayList<Integer> arrayList = (ArrayList<Integer>) map.get("fileBoxId");

        if (arrayList.size() == 0) {
            return new ResultDTO<>(new NotFoundException("id不能为空"));
        }

        // 转换成list
        List<Integer> fileBoxIdList = new LinkedList<>(arrayList);
        return new ResultDTO<>(fileBoxService.listFileBoxesByIdList(fileBoxIdList));
    }

    /**
     * 创建文档集
     *
     * @param map title 标题
     *            desc 描述
     *            count 文件数量
     *            tags 标签
     *            files 文件对象数组
     *            fileName 文件名
     *            numberOrder 文件序号
     *            path 路径
     *            type 类型
     *
     *            fileBoxId 文档集id 自增id
     */
    @PostMapping({"/", ""})
    public ResultDTO<String> postFileBox(@RequestBody Map<String, Object> map) {
        // 创建fileBox
        FileBoxDO fileBoxDO = new FileBoxDO();
        String title = (String) map.get("title");

        fileBoxDO.setTitle(title);
        fileBoxDO.setDesc((String) map.get("desc"));
        fileBoxDO.setCount((Integer) map.get("count"));
        int fileBoxId = fileBoxService.insertFileBox(fileBoxDO); // 获得自增id

        // 创建relation
        ArrayList<String> tags = (ArrayList<String>) map.get("tags");
        for (String s : tags) {
            relationService.insertRelation(new RelationDO(Integer.parseInt(s), fileBoxId));
        }

        // 创建file
        ArrayList<HashMap<String, Object>> files = (ArrayList<HashMap<String, Object>>) map.get("files");
        FileDO fileDO = new FileDO();

        for (HashMap<String, Object> m : files) {

            fileDO.setFileBoxId(fileBoxId);
            fileDO.setFileName((String) m.get("fileName"));
            fileDO.setNumberOrder((Integer) m.get("numberOrder"));
            fileDO.setPath((String) m.get("path"));
            fileDO.setType((String) m.get("type"));

            fileService.insertFile(fileDO);
        }

        return new ResultDTO<>("success");
    }

    /**
     * 删除文档集
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO<String> deleteFileBox(@PathVariable int id) {
        if (fileBoxService.deleteFileBox(id) == 0) {
            return new ResultDTO<>(new NotFoundException("错误，删除失败"));
        } else {
            return new ResultDTO<>("删除成功");
        }
    }

    /**
     * 根据标题、标签获取文档集
     * @param map
     * @return
     */
    @PostMapping("/title/tags")
    public ResultDTO<List<FileBoxDO>> getFileBoxByTitleAndTags(@RequestBody Map<Object, Object> map) {
        // 获取分页数据
        int page = (int) map.get("page");
        int limit = (int) map.get("limit");

        if (!map.containsKey("title") && !map.containsKey("key")) {
            return listAllFileBoxesByLimit(page, limit);
        }

        // 获取标题
        String title = (String) map.get("title");

        // 将前端传入的tags转为set集合 注意string 和 int
        ArrayList<Object> tagsReqObject = (ArrayList<Object>) map.get("tags");

        // 如果标题为空且tag为空，说明查询所有文档集
        if (title.equals("") && tagsReqObject.isEmpty()) {
            return listAllFileBoxesByLimit(page, limit);
        }

        // 如果标题不为空 但 tag为空 直接调接口
        if (!title.equals("") && tagsReqObject.isEmpty()) {
            return new ResultDTO<>(fileBoxService.listFileBoxesByTitle(title));
        }

        ArrayList<Integer> tagsReqInt = new ArrayList<>();
        // 判断tagsReqObject中元素的属性 然后放入list中
        for (Object s : tagsReqObject) {
            if (s instanceof String) {
                tagsReqInt.add(Integer.valueOf((String) s));
            } else {
                tagsReqInt.add((Integer) s);
            }
        }
        Set<Integer> tagsFromReq = new HashSet<>(tagsReqInt);

        // 根据标题、标签筛选之后的id列表
        List<Integer> fileBoxIdList = new LinkedList<>();

        // 根据标题获取文档集与标签的映射关系列表
        List<HashMap<String, Object>> relationList = fileBoxService.listRelationsByTitle(title);

        // 遍历list 取交集 将符合条件的id放入fileBoxIdList中
        for (HashMap<String, Object> m : relationList) {
            // 数据库返回tags的集合
            Set<Integer> tagsFromDB = new HashSet<>();

            // 从map中获取id
            String[] tagArr = ((String) m.get("tag_id")).split(",");
            for (String s : tagArr) {
                tagsFromDB.add(Integer.parseInt(s));
            }

            // 只要交集和之前db查出来的一样，说明符合条件
            tagsFromDB.retainAll(tagsFromReq);
            if (tagsFromReq.equals(tagsFromDB)) {
                // 符合条件 将id放入fileBoxIdList中
                fileBoxIdList.add((Integer) m.get("file_box_id"));
            }
        }

        // 返回的FileBox列表
        List<FileBoxDO> fileBoxDOList;
        if (fileBoxIdList.isEmpty()) {
            return new ResultDTO<>(new NotFoundException("没有数据"));
        }

        fileBoxDOList = fileBoxService.listFileBoxesByIdList(fileBoxIdList);
        int count = fileBoxDOList.size();
        fileBoxDOList = new FileBoxUtils().subList(fileBoxDOList, page, limit);

        return new ResultDTO<>(fileBoxDOList, count);
    }
}
