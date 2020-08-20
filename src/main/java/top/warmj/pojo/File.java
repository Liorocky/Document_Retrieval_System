package top.warmj.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class File {

  private long id;
  private long fileBoxId;
  private long numberOrder;
  private String path;
  private String type;


  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date addTime;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  private Date lastTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getFileBoxId() {
    return fileBoxId;
  }

  public void setFileBoxId(long fileBoxId) {
    this.fileBoxId = fileBoxId;
  }


  public long getNumberOrder() {
    return numberOrder;
  }

  public void setNumberOrder(long numberOrder) {
    this.numberOrder = numberOrder;
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

}
