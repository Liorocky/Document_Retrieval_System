package top.warmj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.warmj.dao.FileBoxDao;
import top.warmj.pojo.FileBox;
import top.warmj.service.FileBoxService;

@Service
public class FileBoxServiceImpl implements FileBoxService {
    @Autowired
    FileBoxDao fileBoxDao;

    @Override
    public FileBox getFileBox(int id) {
        return fileBoxDao.getFileBox(id);
    }

    @Override
    public int postFileBox(FileBox fileBox) {
        return 0;
    }

    @Override
    public int deleteFileBox(int id) {
        return 0;
    }
}
