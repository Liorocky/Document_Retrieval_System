package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;
import top.warmj.pojo.Relation;
import top.warmj.pojo.Result;
import top.warmj.service.RelationService;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/relation")
public class RelationController {
    @Autowired
    RelationService relationService;

    @GetMapping("/{id}")
    public Result<List<Relation>> getRelation(@PathVariable int id) {
        Relation relation = relationService.getRelation(id);
        if (relation == null) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            List<Relation> list = new LinkedList<>();
            list.add(relation);
            return  new Result<>(list);
        }
    }

    @GetMapping({"/", ""})
    public Result<List<Relation>> getAllFile() {
        List<Relation> list = relationService.getAllRelation();
        if (list.size() == 0) {
            return new Result<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new Result<>(list);
        }
    }

    @PostMapping({"/", ""})
    public Result<String> postRelation(@RequestBody Relation relation) {
        if (relationService.postRelation(relation) == 0) {
            return new Result<>(new NotFoundException("错误，添加失败"));
        } else {
            return new Result<>("添加成功");
        }
    }

    @DeleteMapping("/{id}")
    public Result<String> deleteFile(@PathVariable int id) {
        if (relationService.deleteRelation(id) == 0) {
            return new Result<>(new NotFoundException("错误，删除失败"));
        } else {
            return new Result<>("删除成功");
        }
    }
}
