package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.FileDao;
import top.warmj.entity.FileDO;
import top.warmj.service.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override
    public FileDO getFile(int id) {
        return fileDao.getFile(id);
    }

    @Override
    public List<FileDO> listAllFiles() {
        return fileDao.listAllFiles();
    }

    @Override
    public List<FileDO> listFilesByFileBoxId(int id) {
        return fileDao.listFilesByFileBoxId(id);
    }

    @Override
    public int insertFile(FileDO fileDO) {
        return fileDao.insertFile(fileDO);
    }

    @Override
    public int deleteFile(int id) {
        return fileDao.deleteFile(id);
    }

    @Override
    public List<FileDO> listFilesByIdList(List<Integer> idList) {
        return fileDao.listFilesByIdList(idList);
    }
}
