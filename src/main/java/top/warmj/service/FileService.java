package top.warmj.service;

import top.warmj.entity.FileDO;

import java.util.List;

public interface FileService {
    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    FileDO getFile(int id);

    /**
     * 获取所有文件
     * @return
     */
    List<FileDO> listAllFiles();

    /**
     * 获取某个文档集下的所有文件
     * @param id
     * @return
     */
    List<FileDO> listFilesByFileBoxId(int id);

    /**
     * 创建文件
     * @param fileDO
     * @return
     */
    int insertFile(FileDO fileDO);

    /**
     * 删除文件
     * @param id
     * @return
     */
    int deleteFile(int id);

    /**
     * 通过idList获取部分文件
     * @param idList
     * @return
     */
    List<FileDO> listFilesByIdList(List<Integer> idList);
}
