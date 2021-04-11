package top.warmj.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.warmj.model.entity.FileBoxDO;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface FileBoxDao {

    List<FileBoxDO> listAllFileBoxes(String uid);

    /**
     * 获取文档集 分页
     * @return
     */
    List<FileBoxDO> listAllFileBoxesByLimit(int page, int limit);

    List<FileBoxDO> listFileBoxesByIdList(List<Integer> idList);

    /**
     * 通过idList获取文档集 分页
     * @param idList
     * @param page  第几页
     * @param limit 每页最多数据
     * @return
     */
    List<FileBoxDO> listFileBoxesByIdListLimit(List<Integer> idList, int page, int limit);

    int insertFileBox(FileBoxDO fileBoxDO);

    int deleteFileBox(int id);

    List<HashMap<String, Object>> listRelationsByTitle(String title);

    List<FileBoxDO> listFileBoxesByTitle(String title);

    int updateFileBoxActive(int id);
}
