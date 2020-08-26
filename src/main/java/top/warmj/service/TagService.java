package top.warmj.service;

import top.warmj.pojo.Tag;

import java.util.List;

public interface TagService {
    /**
     * 根据id获取标签
     * @param id
     * @return
     */
    Tag getTag(int id);

    /**
     * 获取所有标签
     * @return
     */
    List<Tag> getAllTag();

    /**
     * 创建标签
     * @param tag
     * @return
     */
    int postTag(Tag tag);

    /**
     * 删除标签
     * @param id
     * @return
     */
    int deleteTag(int id);
}
