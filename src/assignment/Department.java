package assignment;

public class Department {

  private String code;
  private String name;
  private String location;

  public Department(String code, String name, String location) {
    this.code = code;
    this.name = name;
    this.location = location;
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

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Department{");
    if (code != null) sb.append("code='").append(code).append('\'');
    if (name != null) sb.append(", name='").append(name).append('\'');
    if (location != null) sb.append(", location='").append(location).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
