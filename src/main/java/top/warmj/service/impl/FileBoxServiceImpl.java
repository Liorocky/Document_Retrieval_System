package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.FileBoxDao;
import top.warmj.pojo.FileBox;
import top.warmj.service.FileBoxService;

import java.util.HashMap;
import java.util.List;

@Service
public class FileBoxServiceImpl implements FileBoxService {
    @Autowired
    FileBoxDao fileBoxDao;

    @Override
    public List<FileBox> getAllFileBox() {
        return fileBoxDao.getAllFileBox();
    }

    @Override
    public List<FileBox> getAllFileBoxLimit(int page, int limit) {
        return fileBoxDao.getAllFileBoxLimit(page, limit);
    }

    @Override
    public List<FileBox> getFileBoxByIdList(List<Integer> idList) {
        return fileBoxDao.getFileBoxByIdList(idList);
    }

    @Override
    public List<FileBox> getFileBoxByIdListLimit(List<Integer> idList, int page, int limit) {
        return fileBoxDao.getFileBoxByIdListLimit(idList, page, limit);
    }

    @Override
    public int postFileBox(FileBox fileBox) {
        fileBoxDao.postFileBox(fileBox);
        return (int) fileBox.getId(); // 返回自增id
    }

    @Override
    public int deleteFileBox(int id) {
        return fileBoxDao.deleteFileBox(id);
    }

    @Override
    public List<HashMap<String, Object>> getRelationListByTitle(String title) {
        return fileBoxDao.getRelationListByTitle(title);
    }

    @Override
    public List<FileBox> getFileBoxByTitle(String title) {
        return fileBoxDao.getFileBoxByTitle(title);
    }
}
