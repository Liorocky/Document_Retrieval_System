package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.TagDao;
import top.warmj.pojo.Tag;
import top.warmj.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public Tag getTag(int id) {
        return tagDao.getTag(id);
    }

    @Override
    public int postTag(Tag tag) {
        return tagDao.postTag(tag);
    }

    @Override
    public int deleteTag(int id) {
        return tagDao.deleteTag(id);
    }
}
