package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.FileDao;
import top.warmj.pojo.File;
import top.warmj.service.FileService;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override
    public List<File> getFile(int fileBoxId) {
        return fileDao.getFile(fileBoxId);
    }
}
