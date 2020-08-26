package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;
import top.warmj.pojo.Result;
import top.warmj.service.FileBoxService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/fileBox")
public class FileBoxController {
    @Autowired
    FileBoxService fileBoxService;

    /**
     * 根据id获取文档集
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
            return  new Result<>(list);
        }
    }

    /**
     * 获取所有文档集
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
     * @param fileBox
     * @return
     */
    @PostMapping({"/", ""})
    public Result<String> postFileBox(@RequestBody FileBox fileBox) {
        if (fileBoxService.postFileBox(fileBox) == 0) {
            return new Result<>(new NotFoundException("错误，添加失败"));
        } else {
            return new Result<>("添加成功");
        }
    }

    /**
     * 删除文档集
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
            return  new Result<>(list);
        }
    }
}
