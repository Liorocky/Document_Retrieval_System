package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.entity.TagDO;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    TagDO getTag(int id);

    List<TagDO> listAllTags();

    int postTag(TagDO tagDO);

    int deleteTag(int id);

}
