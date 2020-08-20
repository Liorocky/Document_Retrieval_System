package top.warmj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.warmj.pojo.FileBox;
import top.warmj.service.FileBoxService;

@RestController
@RequestMapping("fileBox")
public class FileBoxController {
    @Autowired
    FileBoxService fileBoxService;

    @GetMapping("/{id}")
    public FileBox getFileBox(@PathVariable int id) {
        return fileBoxService.getFileBox(id);
    }

}
