package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.Tag;

import java.util.List;

@Mapper
@Repository
public interface TagDao {
    Tag getTag(int id);

    int postTag(Tag tag);

    int deleteTag(int id);
}
