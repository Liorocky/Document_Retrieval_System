package top.warmj.service;

import top.warmj.pojo.File;
import top.warmj.pojo.FileBox;

import java.util.List;

public interface FileBoxService {

    FileBox getFileBox(int id);

    List<File> getFiles(int id);

    int postFileBox(FileBox fileBox);

    int deleteFileBox(int id);
}
