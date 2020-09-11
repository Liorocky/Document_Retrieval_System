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

    /**
     * 获取所有文档集
     *
     * @return
     */
    @GetMapping({"/", ""})
    public Result<List<FileBox>> getFileBoxByIdList() {
        return new Result<>(fileBoxService.getAllFileBox());
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
     *            <p>
     *            fileBoxId 文档集id 自增id
     */
    @PostMapping({"/", ""})
    public Result<String> postFileBox(@RequestBody Map<String, Object> map) {
        // 创建fileBox
        FileBox fileBox = new FileBox();
        fileBox.setTitle((String) map.get("title"));
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

        return new Result<>("as");
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
    @GetMapping("/title/tags")
    public Result<List<FileBox>> getFileBoxByTitleAndTags(@RequestBody Map<String, Object> map) {
        // 获取标题
        String title = (String) map.get("title");
        // 将前端传入的tags转为set集合
        Set<Integer> set1 = new HashSet<>((ArrayList<Integer>) map.get("tags"));
        // 数据库返回tags的集合
        Set<Integer> set2 = new HashSet<>();
        // 根据标题、标签筛选之后的id列表
        List<Integer> fileBoxIdList = new LinkedList<>();

        // 获取映射关系
        List<HashMap<String, Object>> list = fileBoxService.getFileBoxByTitle(title);
        // 遍历list 取交集 将符合条件的id放入fileBoxIdList中
        for (HashMap<String, Object> m : list) {
            set2.clear(); // 清空临时set
            // 从map中获取id
            String[] tagArr = ((String) m.get("tag_id")).split(",");
            for (int i = 0; i < tagArr.length; i++) {
                set2.add(Integer.parseInt(tagArr[i]));
            }

            // 交集判断
            if (!set1.retainAll(set2)) {
                // 符合条件 将id放入fileBoxIdList中
                fileBoxIdList.add((Integer) m.get("file_box_id"));
            }
        }

        // 返回的FileBox列表
        List<FileBox> fileBoxList;
        try {
            fileBoxList = fileBoxService.getFileBoxByIdList(fileBoxIdList);
        } catch (Exception e) {
            // 返回异常消息
            return new Result<>(e);
        }
        return new Result<>(fileBoxList);
    }
}
