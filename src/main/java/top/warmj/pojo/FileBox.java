package top.warmj.pojo;


public class FileBox {

  private long id;
  private String title;
  private String desc;
  private long count;
  private java.sql.Timestamp addTime;
  private java.sql.Timestamp lastTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public long getCount() {
    return count;
  }

  public void setCount(long count) {
    this.count = count;
  }


  public java.sql.Timestamp getAddTime() {
    return addTime;
  }

  public void setAddTime(java.sql.Timestamp addTime) {
    this.addTime = addTime;
  }


  public java.sql.Timestamp getLastTime() {
    return lastTime;
  }

  public void setLastTime(java.sql.Timestamp lastTime) {
    this.lastTime = lastTime;
  }

}
