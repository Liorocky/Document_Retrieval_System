package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface FileBoxDao {

    List<FileBox> getAllFileBox();

    List<FileBox> getFileBoxByIdList(List<Integer> idList);

    int postFileBox(FileBox fileBox);

    int deleteFileBox(int id);

    List<HashMap<String, Object>> getRelationListByTitle(String title);

    List<FileBox> getFileBoxByTitle(String title);

}
