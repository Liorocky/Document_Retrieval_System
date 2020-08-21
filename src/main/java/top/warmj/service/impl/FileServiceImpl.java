package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.FileDao;
import top.warmj.pojo.File;
import top.warmj.service.FileService;

import java.util.Date;
import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileDao fileDao;

    @Override
    public File getFile(int id) {
        return fileDao.getFile(id);
    }

    @Override
    public List<File> getAllFile() {
        return fileDao.getAllFile();
    }

    @Override
    public int postFile(File file) {
        return fileDao.postFile(file);
    }

    @Override
    public int deleteFile(int id) {
        return fileDao.deleteFile(id);
    }
}
