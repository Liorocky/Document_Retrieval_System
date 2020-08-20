package top.warmj.service;

import top.warmj.pojo.File;

import java.util.Date;
import java.util.List;

public interface FileService {
    List<File> getFile(int fileBoxId);

    int postFile(File file);

    int deleteFile(int id);
}
