package assignment;

import java.sql.Date;

public class Drama {

  private String code;
  private String name;
  private String producer;
  private String broadcastStation;
  private Date broadcastDate;

  public Drama(String code, String name, String producer, String broadcastStation, Date broadcastDate) {
    this.code = code;
    this.name = name;
    this.producer = producer;
    this.broadcastStation = broadcastStation;
    this.broadcastDate = broadcastDate;
  }

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

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Drama{");
    if(code != null) sb.append("code='").append(code).append('\'');
    if(name != null) sb.append(", name='").append(name).append('\'');
    if(producer != null) sb.append(", producer='").append(producer).append('\'');
    if(broadcastStation != null) sb.append(", broadcastStation='").append(broadcastStation).append('\'');
    if(broadcastDate != null) sb.append(", broadcastDate=").append(broadcastDate);
    sb.append('}');
    return sb.toString();
  }
}
