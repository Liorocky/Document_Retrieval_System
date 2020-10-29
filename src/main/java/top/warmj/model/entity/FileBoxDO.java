package top.warmj.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FileBoxDO {

  private int id;
  private int active;
  private String title;
  private String desc;
  private int count;

  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
  private Date addTime;
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
  private Date lastTime;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getActive() {
    return active;
  }

  public void setActive(int active) {
    this.active = active;
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
    return "FileBoxDO{" +
            "id=" + id +
            ", active=" + active +
            ", title='" + title + '\'' +
            ", desc='" + desc + '\'' +
            ", count=" + count +
            ", addTime=" + addTime +
            ", lastTime=" + lastTime +
            '}';
  }
}
