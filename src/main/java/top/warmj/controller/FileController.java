package top.warmj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.File;
import top.warmj.service.FileService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping(value = "/{fileBoxId}")
    public List<File> getFile(@PathVariable int fileBoxId) {
        // 处理"/users/{fileBoxId}"的GET请求，用来获取url中fileBoxId值的File信息
        // url中的fileBoxId可通过@PathVariable绑定到函数的参数中
        return fileService.getFile(fileBoxId);
    }

    @PostMapping(value = "/")
    @ResponseBody
    public String postFile(@RequestBody File file) {
        if (fileService.postFile(file) == 0) {
            return "fail";
        } else {
            return "success";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteFile(@PathVariable int id) {
        if (fileService.deleteFile(id) == 0) {
            return "fail";
        } else {
            return "success";
        }
    }
}
