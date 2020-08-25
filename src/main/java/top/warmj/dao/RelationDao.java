package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.Relation;

import java.util.List;

@Repository
@Mapper
public interface RelationDao {
    Relation getRelation(int id);

    List<Relation> getAllRelation();

    int postRelation(Relation relation);

    int deleteRelation(int id);
}
