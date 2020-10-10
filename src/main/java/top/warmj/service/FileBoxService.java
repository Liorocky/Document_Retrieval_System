package top.warmj.service;

import org.omg.PortableInterceptor.INACTIVE;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;

import java.util.HashMap;
import java.util.List;

public interface FileBoxService {

    /**
     * 获取所有文档集
     * @return
     */
    List<FileBox> getAllFileBox();

    /**
     * 获取文档集 分页
     * @return
     */
    List<FileBox> getAllFileBoxLimit(int page, int limit);

    /**
     * 通过idList获取文档集
     * @return
     */
    List<FileBox> getFileBoxByIdList(List<Integer> idList);

    /**
     * 通过idList获取文档集 分页
     * @param idList
     * @param page  第几页
     * @param limit 每页最多数据
     * @return
     */
    List<FileBox> getFileBoxByIdListLimit(List<Integer> idList, int page, int limit);

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
     * 根据标题获取文档集映射关系
     * @param title
     * @return
     */
    List<HashMap<String, Object>> getRelationListByTitle(String title);

    /**
     * 根据标题获取文档集
     * @param title
     * @return
     */
    List<FileBox> getFileBoxByTitle(String title);

}
