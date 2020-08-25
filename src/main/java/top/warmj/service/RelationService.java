package top.warmj.service;

import top.warmj.pojo.File;
import top.warmj.pojo.Relation;

import java.util.List;

public interface RelationService {
    Relation getRelation(int id);

    List<Relation> getAllRelation();

    int postRelation(Relation relation);

    int deleteRelation(int id);
}
