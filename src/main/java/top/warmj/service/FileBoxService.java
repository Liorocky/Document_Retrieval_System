package top.warmj.service;

import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;

import java.util.List;

public interface FileBoxService {

    /**
     * 根据id获取某个文档集
     * @param id
     * @return
     */
    FileBox getFileBox(int id);

    /**
     * 获取所有文档集
     * @return
     */
    List<FileBox> getAllFileBox();

    /**
     * 根据某个文档集中的所有文件
     * @param id
     * @return
     */
    List<File> getFiles(int id);

    /**
     * 创建文档集
     * @param fileBox
     * @return
     */
    int postFileBox(FileBox fileBox);

    /**
     * 删除文档集
     * @param id
     * @return
     */
    int deleteFileBox(int id);

    /**
     * 根据标题获取文档集
     * @param title
     * @return
     */
    List<FileBox> getFileBoxByTitle(String title);
}
