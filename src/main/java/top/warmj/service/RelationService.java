package top.warmj.service;

import top.warmj.entity.RelationDO;

import java.util.List;

public interface RelationService {
    /**
     * 根据id获取映射关系
     * @param id
     * @return
     */
    RelationDO getRelation(int id);

    /**
     * 获取所有映射关系
     * @return
     */
    List<RelationDO> listAllRelations();

    /**
     * 创建映射关系
     * @param relationDO
     * @return
     */
    int insertRelation(RelationDO relationDO);

    /**
     * 删除映射关系
     * @param id
     * @return
     */
    int deleteRelation(int id);
}
