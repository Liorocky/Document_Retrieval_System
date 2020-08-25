package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.RelationDao;
import top.warmj.pojo.Relation;
import top.warmj.service.RelationService;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    RelationDao relationDao;

    @Override
    public Relation getRelation(int id) {
        return relationDao.getRelation(id);
    }

    @Override
    public List<Relation> getAllRelation() {
        return relationDao.getAllRelation();
    }

    @Override
    public int postRelation(Relation relation) {
        return relationDao.postRelation(relation);
    }

    @Override
    public int deleteRelation(int id) {
        return relationDao.deleteRelation(id);
    }
}
