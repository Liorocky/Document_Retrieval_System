package top.warmj.service;

import top.warmj.pojo.FileBox;

public interface FileBoxService {

    FileBox getFileBox(int id);

    int postFileBox(FileBox fileBox);

    int deleteFileBox(int id);
}
