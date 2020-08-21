package top.warmj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;
import top.warmj.service.FileBoxService;

import java.util.List;

@RestController
@RequestMapping("/fileBox")
public class FileBoxController {
    @Autowired
    FileBoxService fileBoxService;

    @GetMapping("/{id}")
    public FileBox getFileBox(@PathVariable int id) {
        return fileBoxService.getFileBox(id);
    }

    @GetMapping("/{id}/files")
    public List<File> getFiles(@PathVariable int id) {
        return fileBoxService.getFiles(id);
    }

    @PostMapping({"/", ""})
    public String postFileBox(@RequestBody FileBox fileBox) {
        if (fileBoxService.postFileBox(fileBox) == 0) {
            return "failed";
        } else {
            return "success";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteFileBox(@PathVariable int id) {
        if (fileBoxService.deleteFileBox(id) == 0) {
            return "failed";
        } else {
            return "success";
        }
    }
}
