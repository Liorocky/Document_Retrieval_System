package top.warmj.utils;

import top.warmj.pojo.FileBox;

import java.util.LinkedList;
import java.util.List;

/**
 * 1 10      0 ~ 10
 * 2 10      10 ~ 20
 * 3 10      20 ~ 30
 */
public class FileBoxUtils {
    public List<FileBox> subList(List<FileBox> source, int page, int limit) {
        List<FileBox> target = new LinkedList<>();
        int min = Math.min(source.size(), page * limit);
        target = source.subList((page - 1) * limit, min);

        return target;
    }
}
