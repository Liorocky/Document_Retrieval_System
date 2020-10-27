package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.mapper.FileBoxDao;
import top.warmj.model.entity.FileBoxDO;
import top.warmj.service.FileBoxService;

import java.util.HashMap;
import java.util.List;

@Service
public class FileBoxServiceImpl implements FileBoxService {
    @Autowired
    FileBoxDao fileBoxDao;

    @Override
    public List<FileBoxDO> listAllFileBoxes() {
        return fileBoxDao.listAllFileBoxes();
    }

    @Override
    public List<FileBoxDO> listAllFileBoxesByLimit(int page, int limit) {
        return fileBoxDao.listAllFileBoxesByLimit(page, limit);
    }

    @Override
    public List<FileBoxDO> listFileBoxesByIdList(List<Integer> idList) {
        return fileBoxDao.listFileBoxesByIdList(idList);
    }

    @Override
    public List<FileBoxDO> listFileBoxesByIdListLimit(List<Integer> idList, int page, int limit) {
        return fileBoxDao.listFileBoxesByIdListLimit(idList, page, limit);
    }

    @Override
    public int insertFileBox(FileBoxDO fileBoxDO) {
        fileBoxDao.insertFileBox(fileBoxDO);
        return (int) fileBoxDO.getId(); // 返回自增id
    }

    @Override
    public int deleteFileBox(int id) {
        return fileBoxDao.deleteFileBox(id);
    }

    @Override
    public List<HashMap<String, Object>> listRelationsByTitle(String title) {
        return fileBoxDao.listRelationsByTitle(title);
    }

    @Override
    public List<FileBoxDO> listFileBoxesByTitle(String title) {
        return fileBoxDao.listFileBoxesByTitle(title);
    }
}
