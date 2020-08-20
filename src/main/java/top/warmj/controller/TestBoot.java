package top.warmj.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.warmj.pojo.File;

@RestController
@RequestMapping("test")
public class TestBoot {

    @RequestMapping("file")
    public File test() {
        File file = new File();
        file.setPath("asas");
        file.setId(1);
        return file;
    }
}
