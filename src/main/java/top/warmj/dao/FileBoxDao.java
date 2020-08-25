package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;

import java.util.List;

@Mapper
@Repository
public interface FileBoxDao {
    FileBox getFileBox(int id);

    int postFileBox(FileBox fileBox);

    int deleteFileBox(int id);

    List<File> getFiles(int id);

    List<FileBox> getAllFileBox();

    List<FileBox> getFileBoxByTitle(String title);

}
