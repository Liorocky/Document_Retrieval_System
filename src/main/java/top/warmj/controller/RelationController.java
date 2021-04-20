package top.warmj.controller;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.warmj.model.dto.ResultDTO;
import top.warmj.model.entity.RelationDO;
import top.warmj.service.RelationService;

import java.util.LinkedList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/relation")
public class RelationController {
    @Autowired
    RelationService relationService;

    /**
     * 根据id获取映射关系
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResultDTO<List<RelationDO>> getRelation(@PathVariable int id) {
        RelationDO relationDO = relationService.getRelation(id);
        if (relationDO == null) {
            return new ResultDTO<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            List<RelationDO> list = new LinkedList<>();
            list.add(relationDO);
            return  new ResultDTO<>(list);
        }
    }

    /**
     * 获取所有映射关系
     * @return
     */
    @GetMapping({"/", ""})
    public ResultDTO<List<RelationDO>> listAllRelations() {
        List<RelationDO> list = relationService.listAllRelations();
        if (list.size() == 0) {
            return new ResultDTO<>(new NotFoundException("错误，数据库中未查到相关资源"));
        } else {
            return new ResultDTO<>(list);
        }
    }

    /**
     * 创建映射关系
     * @param relationDO
     * @return
     */
    @PostMapping({"/", ""})
    public ResultDTO<String> insertRelation(@RequestBody RelationDO relationDO) {
        if (relationService.insertRelation(relationDO) == 0) {
            return new ResultDTO<>(new NotFoundException("错误，添加失败"));
        } else {
            return new ResultDTO<>("添加成功");
        }
    }

    /**
     * 删除映射关系
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResultDTO<String> deleteFile(@PathVariable int id) {
        if (relationService.deleteRelation(id) == 0) {
            return new ResultDTO<>(new NotFoundException("错误，删除失败"));
        } else {
            return new ResultDTO<>("删除成功");
        }
    }
}
