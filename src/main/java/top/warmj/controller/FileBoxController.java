package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.model.dto.FileDetailDTO;
import top.warmj.model.entity.FileDO;
import top.warmj.model.entity.FileBoxDO;
import top.warmj.model.entity.RelationDO;
import top.warmj.model.dto.ResultDTO;
import top.warmj.model.entity.TagDO;
import top.warmj.query.FileBoxSearchQuery;
import top.warmj.query.FileBoxUploadQuery;
import top.warmj.service.FileBoxService;
import top.warmj.service.FileService;
import top.warmj.service.RelationService;
import top.warmj.service.TagService;
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

    @Autowired
    TagService tagService;

    private static final String FILEPATH = "/usr/local/drs/upload/"; // 存储路径
//    private static final String FILEPATH = "D:/upload/"; // 存储路径

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
        List<FileBoxDO> fileBoxList = fileBoxService.listAllFileBoxesByLimit((page - 1) * limit, limit);
        int fileBoxCount = fileBoxService.listAllFileBoxes().size();
        return new ResultDTO<>(fileBoxList, fileBoxCount);
    }

    /**
     * @title 创建文档集
     * @description 创建文档集
     * @author WarMj
     * @param fileBoxUploadQuery
     * @updateTime 2020/10/27 10:38
     * @return 
     * @throws 
     */
    @PostMapping({"/", ""})
    public ResultDTO<String> insertFileBox(@RequestBody FileBoxUploadQuery fileBoxUploadQuery) {
        // 创建fileBox
        FileBoxDO fileBoxDO = new FileBoxDO();

        fileBoxDO.setTitle(fileBoxUploadQuery.getTitle());
        fileBoxDO.setDesc(fileBoxUploadQuery.getDesc());
        fileBoxDO.setCount(fileBoxUploadQuery.getCount());
        int fileBoxId = fileBoxService.insertFileBox(fileBoxDO); // 获得自增id

        // 创建relation
        ArrayList<String> tagList = fileBoxUploadQuery.getTags();
        for (String s : tagList) {
            relationService.insertRelation(new RelationDO(Integer.parseInt(s), fileBoxId));
        }

        // 创建file
        ArrayList<FileDO> fileList = fileBoxUploadQuery.getFiles();
        FileDO fileDO = new FileDO();

        for (FileDO f : fileList) {
            fileDO.setFileBoxId(fileBoxId);
            fileDO.setFileName(f.getFileName());
            fileDO.setNumberOrder(f.getNumberOrder());
            fileDO.setPath(f.getPath());
            fileDO.setType(f.getType());

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
     * @param
     * @return
     */
    @PostMapping("/title/tags")
    public ResultDTO<List<FileBoxDO>> getFileBoxByTitleAndTags(@RequestBody FileBoxSearchQuery query) {
        // 获取分页数据
        int page = query.getPage();
        int limit = query.getLimit();

        if (query.getTitle() == null && query.getKey() == null) {
            return listAllFileBoxesByLimit(page, limit);
        }

        // 获取标题
        String title = query.getTitle();

        // 将前端传入的tags转为set集合 注意string 和 int
        ArrayList<Object> tagsReqObject = query.getTagList();

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


    /**
     * 获取某个文档集中的所有文件
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultDTO<FileDetailDTO> listFilesByFileBoxId(@PathVariable int id) {
        List<FileDO> fileDOList = fileService.listFilesByFileBoxId(id);
        List<TagDO> tagDOList = tagService.listTagsByFileBoxId(id);

        FileDetailDTO fileDetailDTO = new FileDetailDTO();
        fileDetailDTO.setFileDOList(fileDOList);
        fileDetailDTO.setTagDOList(tagDOList);

        if (fileDOList.size() == 0) {
            return new ResultDTO<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new ResultDTO<>(fileDetailDTO);
        }
    }

    /**
     * @title 更新fileBox的存活状态
     * @description 
     * @author WarMj
     * @param 
     * @updateTime 2020/10/29 15:14
     * @return 
     * @throws 
     */
    @PutMapping("/{id}")
    public ResultDTO<String> updateFileBoxActive(@PathVariable int id) {
        int result = fileBoxService.updateFileBoxActive(id);
        if (result == 0) {
            return new ResultDTO<>(new Exception("删除失败"));
        } else {
            return new ResultDTO<>("success");
        }
    }
}
