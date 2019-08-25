package assignment;

import java.sql.Date;

public class Drama {

  private String code;
  private String name;
  private String producer;
  private String broadcastStation;
  private Date broadcastDate;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBroadcastStation() {
    return broadcastStation;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public void setBroadcastStation(String broadcastStation) {
    this.broadcastStation = broadcastStation;
  }

  public Date getBroadcastDate() {
    return broadcastDate;
  }

  public void setBroadcastDate(Date broadcastDate) {
    this.broadcastDate = broadcastDate;
  }
}
