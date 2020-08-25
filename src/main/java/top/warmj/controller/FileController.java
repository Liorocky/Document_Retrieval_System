package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.File;
import top.warmj.pojo.Result;
import top.warmj.service.FileService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/{id}")
    public Result<List<File>> getFile(@PathVariable int id) {
        File file = fileService.getFile(id);
        if (file == null) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            List<File> list = new LinkedList<>();
            list.add(file);
            return new Result<>(list);
        }
    }

    @GetMapping({"/", ""})
    public Result<List<File>> getAllFile() {
        List<File> list = fileService.getAllFile();
        if (list.size() == 0) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new Result<>(list);
        }
    }

    @PostMapping({"/", ""})
    public Result<String> postFile(@RequestBody File file) {
        if (fileService.postFile(file) == 0) {
            return new Result<>(new NotFoundException("错误，添加失败"));
        } else {
            return new Result<>("添加成功");
        }
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteFile(@PathVariable int id) {
        if (fileService.deleteFile(id) == 0) {
            return new Result<>(new NotFoundException("错误，删除失败"));
        } else {
            return new Result<>("删除成功");
        }
    }
}
