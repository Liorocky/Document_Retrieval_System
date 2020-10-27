package top.warmj.model.entity;


public class RelationDO {

  private int id;
  private int tagId;
  private int fileBoxId;

  public RelationDO(int tagId, int fileBoxId) {
    this.tagId = tagId;
    this.fileBoxId = fileBoxId;
  }

  public RelationDO(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public int getTagId() {
    return tagId;
  }

  public void setTagId(int tagId) {
    this.tagId = tagId;
  }


  public int getFileBoxId() {
    return fileBoxId;
  }

  public void setFileBoxId(int fileBoxId) {
    this.fileBoxId = fileBoxId;
  }

}
