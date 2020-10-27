package top.warmj.service;

import top.warmj.model.entity.TagDO;

import java.util.List;

public interface TagService {
    /**
     * 根据id获取标签
     * @param id
     * @return
     */
    TagDO getTag(int id);

    /**
     * 获取所有标签
     * @return
     */
    List<TagDO> listAllTags();

    /**
     * 创建标签
     * @param tagDO
     * @return
     */
    int postTag(TagDO tagDO);

    /**
     * 删除标签
     * @param id
     * @return
     */
    int deleteTag(int id);

    /**
     * 根据fileBoxId获取标签
     * @param id
     * @return
     */
    List<TagDO> listTagsByFileBoxId(int id);
}
