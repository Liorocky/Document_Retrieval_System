package top.warmj.service;

import top.warmj.pojo.File;

import java.util.List;

public interface FileService {
    File getFile(int id);

    List<File> getAllFile();

    int postFile(File file);

    int deleteFile(int id);
}
