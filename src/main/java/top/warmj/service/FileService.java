package top.warmj.service;

import top.warmj.pojo.File;

import java.util.List;

public interface FileService {
    /**
     * 根据id获取文件
     * @param id
     * @return
     */
    File getFile(int id);

    /**
     * 获取所有文件
     * @return
     */
    List<File> getAllFile();

    /**
     * 获取某个文档集下的所有文件
     * @param id
     * @return
     */
    List<File> getFiles(int id);

    /**
     * 创建文件
     * @param file
     * @return
     */
    int postFile(File file);

    /**
     * 删除文件
     * @param id
     * @return
     */
    int deleteFile(int id);
}
