package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.mapper.RelationDao;
import top.warmj.model.entity.RelationDO;
import top.warmj.service.RelationService;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {
    @Autowired
    RelationDao relationDao;

    @Override
    public RelationDO getRelation(int id) {
        return relationDao.getRelation(id);
    }

    @Override
    public List<RelationDO> listAllRelations() {
        return relationDao.listAllRelations();
    }

    @Override
    public int insertRelation(RelationDO relationDO) {
        return relationDao.insertRelation(relationDO);
    }

    @Override
    public int deleteRelation(int id) {
        return relationDao.deleteRelation(id);
    }
}
