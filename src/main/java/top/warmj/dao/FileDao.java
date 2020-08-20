package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.File;

import java.util.List;

@Repository
@Mapper
public interface FileDao {
    List<File> getFile(int fileBoxId);
}
