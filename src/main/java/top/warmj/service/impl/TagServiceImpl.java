package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.mapper.TagDao;
import top.warmj.model.entity.TagDO;
import top.warmj.service.TagService;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    TagDao tagDao;

    @Override
    public TagDO getTag(int id) {
        return tagDao.getTag(id);
    }

    @Override
    public List<TagDO> listAllTags() {
        return tagDao.listAllTags();
    }

    @Override
    public int postTag(TagDO tagDO) {
        return tagDao.postTag(tagDO);
    }

    @Override
    public int deleteTag(int id) {
        return tagDao.deleteTag(id);
    }

    @Override
    public List<TagDO> listTagsByFileBoxId(int id) {
        return tagDao.listTagsByFileBoxId(id);
    }
}
