package top.warmj.query;

import top.warmj.model.entity.FileDO;

import java.util.ArrayList;

/**
 * @author WarMj
 * @ClassName FileBoxQuery.java
 * @Description TODO
 * @createTime 2020年10月27日 10:22:00
 */
public class FileBoxUploadQuery {
    private String title;
    private String desc;
    private String uid;
    private int count;
    private ArrayList<String> tags;
    private ArrayList<FileDO> files;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<FileDO> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FileDO> files) {
        this.files = files;
    }
}
