package top.warmj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.Tag;
import top.warmj.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/{id}")
    public Tag getTag(@PathVariable int id) {
        return tagService.getTag(id);
    }

    @PostMapping({"/", ""})
    public String postTag(@RequestBody Tag tag) {
        if (tagService.postTag(tag) == 0) {
            return "failed";
        } else {
            return "success";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteTag(@PathVariable int id) {
        if (tagService.deleteTag(id) == 0) {
            return "failed";
        } else {
            return "success";
        }
    }
}
