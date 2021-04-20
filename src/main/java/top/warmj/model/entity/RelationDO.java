package top.warmj.model.entity;


public class RelationDO {

  private int id;
  private int tagId;
  private int fileBoxId;
  private String uid;

  public RelationDO(int tagId, int fileBoxId, String uid) {
    this.tagId = tagId;
    this.fileBoxId = fileBoxId;
    this.uid = uid;
  }

  public RelationDO(){}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
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
