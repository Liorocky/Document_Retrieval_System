package top.warmj.service;

import top.warmj.pojo.Relation;

import java.util.List;

public interface RelationService {
    /**
     * 根据id获取映射关系
     * @param id
     * @return
     */
    Relation getRelation(int id);

    /**
     * 获取所有映射关系
     * @return
     */
    List<Relation> getAllRelation();

    /**
     * 创建映射关系
     * @param relation
     * @return
     */
    int postRelation(Relation relation);

    /**
     * 删除映射关系
     * @param id
     * @return
     */
    int deleteRelation(int id);
}
