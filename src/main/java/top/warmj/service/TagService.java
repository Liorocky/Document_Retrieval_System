package top.warmj.service;

import top.warmj.pojo.Tag;

import java.util.List;

public interface TagService {
    Tag getTag(int id);

    int postTag(Tag tag);

    int deleteTag(int id);
}
