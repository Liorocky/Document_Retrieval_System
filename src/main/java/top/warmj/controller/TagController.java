package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.model.dto.ResultDTO;
import top.warmj.model.entity.TagDO;
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
    public ResultDTO<List<TagDO>> getTag(@PathVariable int id) {
        TagDO tagDO = tagService.getTag(id);
        if (tagDO == null) {
            return new ResultDTO<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            List<TagDO> list = new LinkedList<>();
            list.add(tagDO);
            return new ResultDTO<>(list);
        }
    }

    /**
     * 获取所有标签
     * @return
     */
    @GetMapping({"/", ""})
    public ResultDTO<List<TagDO>> listAllTags() {
        List<TagDO> list = tagService.listAllTags();
        if (list.size() == 0) {
            return new ResultDTO<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new ResultDTO<>(list);
        }
    }

    /**
     * 创建标签
     * @param tagDO
     * @return 返回创建好的tag信息
     */
    @PostMapping({"/", ""})
    public ResultDTO<TagDO> postTag(@RequestBody TagDO tagDO) {
        TagDO newTagDO = new TagDO();
        try {
            tagService.postTag(tagDO); // 影响行数
        } catch (Exception e) {
            return new ResultDTO<>(new NotFoundException("错误，重复数据"));
        }

        int id = (int) tagDO.getId(); // 自增id
        if (id == 0) {
            return new ResultDTO<>(new NotFoundException("错误，添加失败"));
        } else {
            newTagDO.setName(tagDO.getName());
            newTagDO.setId(id);
            return new ResultDTO<>(newTagDO);
        }
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO<String> deleteTag(@PathVariable int id) {
        if (tagService.deleteTag(id) == 0) {
            return new ResultDTO<>(new NotFoundException("错误，删除失败"));
        } else {
            return new ResultDTO<>("删除成功");
        }
    }
}
