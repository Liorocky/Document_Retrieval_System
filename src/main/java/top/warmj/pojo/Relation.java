package top.warmj.pojo;


public class Relation {

  private int id;
  private int tagId;
  private int fileBoxId;

  public Relation(int tagId, int fileBoxId) {
    this.tagId = tagId;
    this.fileBoxId = fileBoxId;
  }

  public Relation(){}

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
