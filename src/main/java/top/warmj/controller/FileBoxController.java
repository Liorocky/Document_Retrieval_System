package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;
import top.warmj.pojo.Relation;
import top.warmj.pojo.Result;
import top.warmj.service.FileBoxService;
import top.warmj.service.FileService;
import top.warmj.service.RelationService;
import top.warmj.utils.FileBoxUtils;

import java.text.SimpleDateFormat;
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
    public Result<List<FileBox>> getAllFileBox() {
        return new Result<>(fileBoxService.getAllFileBox());
    }

    /**
     * 获取所有文档集
     * 加上分页参数
     *
     * @return
     */
    @GetMapping({"/parameter"})
    public Result<List<FileBox>> getAllFileBoxLimit(@RequestParam int page, @RequestParam int limit) {
        List<FileBox> list = fileBoxService.getAllFileBoxLimit((page - 1) * limit, limit);
        int count = fileBoxService.getAllFileBox().size();
        return new Result<>(list, count);
    }

    /**
     * 通过idList获取文档集
     * @return
     */
    @GetMapping("/id")
    public Result<List<FileBox>> getFileBoxByIdList(@RequestBody Map<String, Object> map) {
        // 从map中获取id
        ArrayList<Integer> arrayList = (ArrayList<Integer>) map.get("fileBoxId");

        if (arrayList.size() == 0) {
            return new Result<>(new NotFoundException("id不能为空"));
        }

        // 转换成list
        List<Integer> fileBoxIdList = new LinkedList<>(arrayList);
        return new Result<>(fileBoxService.getFileBoxByIdList(fileBoxIdList));
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
    public Result<String> postFileBox(@RequestBody Map<String, Object> map) {
        // 创建fileBox
        FileBox fileBox = new FileBox();
        String title = (String) map.get("title");

        fileBox.setTitle(title);
        fileBox.setDesc((String) map.get("desc"));
        fileBox.setCount((Integer) map.get("count"));
        int fileBoxId = fileBoxService.postFileBox(fileBox); // 获得自增id

        // 创建relation
        ArrayList<String> tags = (ArrayList<String>) map.get("tags");
        for (String s : tags) {
            relationService.postRelation(new Relation(Integer.parseInt(s), fileBoxId));
        }

        // 创建file
        ArrayList<HashMap<String, Object>> files = (ArrayList<HashMap<String, Object>>) map.get("files");
        File file = new File();

        for (HashMap<String, Object> m : files) {

            file.setFileBoxId(fileBoxId);
            file.setFileName((String) m.get("fileName"));
            file.setNumberOrder((Integer) m.get("numberOrder"));
            file.setPath((String) m.get("path"));
            file.setType((String) m.get("type"));

            fileService.postFile(file);
        }

        return new Result<>("success");
    }

    /**
     * 删除文档集
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteFileBox(@PathVariable int id) {
        if (fileBoxService.deleteFileBox(id) == 0) {
            return new Result<>(new NotFoundException("错误，删除失败"));
        } else {
            return new Result<>("删除成功");
        }
    }

    /**
     * 根据标题、标签获取文档集
     * @param map
     * @return
     */
    @PostMapping("/title/tags")
    public Result<List<FileBox>> getFileBoxByTitleAndTags(@RequestBody Map<Object, Object> map) {
        // 获取分页数据
        int page = (int) map.get("page");
        int limit = (int) map.get("limit");

        if (!map.containsKey("title") && !map.containsKey("key")) {
            return getAllFileBoxLimit(page, limit);
        }

        // 获取标题
        String title = (String) map.get("title");

        // 将前端传入的tags转为set集合 注意string 和 int
        ArrayList<Object> tagsReqObject = (ArrayList<Object>) map.get("tags");

        // 如果标题为空且tag为空，说明查询所有文档集
        if (title.equals("") && tagsReqObject.isEmpty()) {
            return getAllFileBoxLimit(page, limit);
        }

        // 如果标题不为空 但 tag为空 直接调接口
        if (!title.equals("") && tagsReqObject.isEmpty()) {
            return new Result<>(fileBoxService.getFileBoxByTitle(title));
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
        List<HashMap<String, Object>> relationList = fileBoxService.getRelationListByTitle(title);

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
        List<FileBox> fileBoxList;
        if (fileBoxIdList.isEmpty()) {
            return new Result<>(new NotFoundException("没有数据"));
        }

        fileBoxList = fileBoxService.getFileBoxByIdList(fileBoxIdList);
        int count = fileBoxList.size();
        fileBoxList = new FileBoxUtils().subList(fileBoxList, page, limit);

        return new Result<>(fileBoxList, count);
    }
}
