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
     * 根据id获取文档集
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<List<FileBox>> getFileBox(@PathVariable int id) {
        FileBox fileBox = fileBoxService.getFileBox(id);
        if (fileBox == null) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            List<FileBox> list = new LinkedList<>();
            list.add(fileBox);
            return new Result<>(list);
        }
    }

    /**
     * 获取所有文档集
     *
     * @return
     */
    @GetMapping({"/", ""})
    public Result<List<FileBox>> getAllFileBox() {
        List<FileBox> list = fileBoxService.getAllFileBox();
        if (list.size() == 0) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new Result<>(list);
        }
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

    @GetMapping("/title/{title}")
    public Result<List<FileBox>> getFileBoxByTitle(@PathVariable String title) {
        List<FileBox> list = fileBoxService.getFileBoxByTitle(title);
        if (list.size() == 0) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new Result<>(list);
        }
    }
}
