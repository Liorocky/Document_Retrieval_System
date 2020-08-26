package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.File;

import java.util.Date;
import java.util.List;

@Repository
@Mapper
public interface FileDao {
    File getFile(int id);

    List<File> getAllFile();

    List<File> getFiles(int id);

    int postFile(File file);

    int deleteFile(int id);

}
