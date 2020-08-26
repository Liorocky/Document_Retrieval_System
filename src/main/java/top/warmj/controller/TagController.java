package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.Result;
import top.warmj.pojo.Tag;
import top.warmj.service.TagService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    /**
     * 根据id获取标签
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<List<Tag>> getTag(@PathVariable int id) {
        Tag tag = tagService.getTag(id);
        if (tag == null) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            List<Tag> list = new LinkedList<>();
            list.add(tag);
            return new Result<>(list);
        }
    }

    /**
     * 获取所有标签
     * @return
     */
    @GetMapping({"/", ""})
    public Result<List<Tag>> getAllTag() {
        List<Tag> list = tagService.getAllTag();
        if (list.size() == 0) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new Result<>(list);
        }
    }

    /**
     * 创建标签
     * @param tag
     * @return
     */
    @PostMapping({"/", ""})
    public Result<String> postTag(@RequestBody Tag tag) {
        if (tagService.postTag(tag) == 0) {
            return new Result<>(new NotFoundException("错误，添加失败"));
        } else {
            return new Result<>("添加成功");
        }
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteTag(@PathVariable int id) {
        if (tagService.deleteTag(id) == 0) {
            return new Result<>(new NotFoundException("错误，删除失败"));
        } else {
            return new Result<>("删除成功");
        }
    }
}
