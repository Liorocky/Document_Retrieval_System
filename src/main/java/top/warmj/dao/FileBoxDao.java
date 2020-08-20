package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.FileBox;

@Mapper
@Repository
public interface FileBoxDao {
    FileBox getFileBox(int id);

    int postFileBox(FileBox fileBox);

    int deleteFileBox(int id);
}
