package top.warmj.service;

import top.warmj.model.entity.FileBoxDO;

import java.util.HashMap;
import java.util.List;

public interface FileBoxService {

    /**
     * 获取所有文档集
     * @return
     */
    List<FileBoxDO> listAllFileBoxes();

    /**
     * 获取文档集 分页
     * @return
     */
    List<FileBoxDO> listAllFileBoxesByLimit(int page, int limit);

    /**
     * 通过idList获取文档集
     * @return
     */
    List<FileBoxDO> listFileBoxesByIdList(List<Integer> idList);

    /**
     * 通过idList获取文档集 分页
     * @param idList
     * @param page  第几页
     * @param limit 每页最多数据
     * @return
     */
    List<FileBoxDO> listFileBoxesByIdListLimit(List<Integer> idList, int page, int limit);

    /**
     * 创建文档集
     * @param fileBoxDO
     * @return
     */
    int insertFileBox(FileBoxDO fileBoxDO);

    /**
     * 删除文档集
     * @param id
     * @return
     */
    int deleteFileBox(int id);

    /**
     * 根据标题获取文档集映射关系
     * @param title
     * @return
     */
    List<HashMap<String, Object>> listRelationsByTitle(String title);

    /**
     * 根据标题获取文档集
     * @param title
     * @return
     */
    List<FileBoxDO> listFileBoxesByTitle(String title);

}
