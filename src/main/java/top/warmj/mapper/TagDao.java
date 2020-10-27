package top.warmj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.model.entity.TagDO;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    TagDO getTag(int id);

    List<TagDO> listAllTags();

    int postTag(TagDO tagDO);

    int deleteTag(int id);

    List<TagDO> listTagsByFileBoxId(int id);
}
