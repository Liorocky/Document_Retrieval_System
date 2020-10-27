package top.warmj.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.entity.FileDO;

import java.util.List;

@Repository
@Mapper
public interface FileDao {
    FileDO getFile(int id);

    List<FileDO> listAllFiles();

    List<FileDO> listFilesByFileBoxId(int id);

    int insertFile(FileDO fileDO);

    int deleteFile(int id);

    List<FileDO> listFilesByIdList(List<Integer> idList);
}
