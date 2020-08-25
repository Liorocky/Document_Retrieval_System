package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.FileBoxDao;
import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;
import top.warmj.service.FileBoxService;

import java.util.List;

@Service
public class FileBoxServiceImpl implements FileBoxService {
    @Autowired
    FileBoxDao fileBoxDao;

    @Override
    public FileBox getFileBox(int id) {
        return fileBoxDao.getFileBox(id);
    }

    @Override
    public List<FileBox> getAllFileBox() {
        return fileBoxDao.getAllFileBox();
    }

    @Override
    public List<File> getFiles(int id) {
        return fileBoxDao.getFiles(id);
    }

    @Override
    public int postFileBox(FileBox fileBox) {
        return fileBoxDao.postFileBox(fileBox);
    }

    @Override
    public int deleteFileBox(int id) {
        return fileBoxDao.deleteFileBox(id);
    }
}
