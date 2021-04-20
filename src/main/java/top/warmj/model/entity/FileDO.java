package top.warmj.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FileDO {

  private int id;
  private int fileBoxId;
  private String uid;
  private String fileName;
  private int numberOrder;
  private int size;
  private String path;
  private String type;


  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date addTime;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date lastTime;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

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

  public int getFileBoxId() {
    return fileBoxId;
  }

  public void setFileBoxId(int fileBoxId) {
    this.fileBoxId = fileBoxId;
  }


  public int getNumberOrder() {
    return numberOrder;
  }

  public void setNumberOrder(int numberOrder) {
    this.numberOrder = numberOrder;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public Date getAddTime() {
    return addTime;
  }

  public void setAddTime(Date addTime) {
    this.addTime = addTime;
  }


  public Date getLastTime() {
    return lastTime;
  }

  public void setLastTime(Date lastTime) {
    this.lastTime = lastTime;
  }

  @Override
  public String toString() {
    return "FileDO{" +
            "id=" + id +
            ", fileBoxId=" + fileBoxId +
            ", fileName='" + fileName + '\'' +
            ", numberOrder=" + numberOrder +
            ", path='" + path + '\'' +
            ", type='" + type + '\'' +
            ", addTime=" + addTime +
            ", lastTime=" + lastTime +
            '}';
  }
}
