package top.warmj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.warmj.pojo.File;
import top.warmj.service.FileService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("files")
public class FileController {
    @Autowired
    FileService fileService;

    @RequestMapping(value="/{fileBoxId}", method=RequestMethod.GET)
    public List<File> getUser(@PathVariable int fileBoxId) {
        // 处理"/users/{fileBoxId}"的GET请求，用来获取url中fileBoxId值的File信息
        // url中的fileBoxId可通过@PathVariable绑定到函数的参数中
        return fileService.getFile(fileBoxId);
    }
}
