package top.warmj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.model.entity.RelationDO;

import java.util.List;

@Repository
@Mapper
public interface RelationDao {
    RelationDO getRelation(int id);

    List<RelationDO> listAllRelations();

    int insertRelation(RelationDO relationDO);

    int deleteRelation(int id);
}
