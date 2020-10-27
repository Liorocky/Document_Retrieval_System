package top.warmj.query;

import java.util.ArrayList;

/**
 * @author WarMj
 * @ClassName FileBoxSearchQuery.java
 * @Description TODO
 * @createTime 2020年10月27日 13:05:00
 */
public class FileBoxSearchQuery {
    private String title;
    private String key;
    private ArrayList<Object> tagList;
    private int page;
    private int limit;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ArrayList<Object> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<Object> tagList) {
        this.tagList = tagList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FileBoxSearchQuery{" +
                "title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", tagList=" + tagList +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }
}
