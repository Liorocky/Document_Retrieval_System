package top.warmj.model.dto;

import top.warmj.model.entity.FileDO;
import top.warmj.model.entity.TagDO;

import java.util.List;

/**
 * @author WarMj
 * @ClassName FileDetailDTO.java
 * @Description
 * @createTime 2020年10月27日 13:50:00
 */
public class FileDetailDTO {
    private List<FileDO> fileDOList;
    private List<TagDO> tagDOList;

    public List<FileDO> getFileDOList() {
        return fileDOList;
    }

    public void setFileDOList(List<FileDO> fileDOList) {
        this.fileDOList = fileDOList;
    }

    public List<TagDO> getTagDOList() {
        return tagDOList;
    }

    public void setTagDOList(List<TagDO> tagDOList) {
        this.tagDOList = tagDOList;
    }

    @Override
    public String toString() {
        return "FileDetailDTO{" +
                "fileDOList=" + fileDOList +
                ", tagDOList=" + tagDOList +
                '}';
    }
}
