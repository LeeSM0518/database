package assignment;

public class Employee {

  private String code;
  private String name;
  private String manager;
  private int salary;
  private String roleCode;

  public Employee(String code, String name, String manager, int salary, String roleCode) {
    this.code = code;
    this.name = name;
    this.manager = manager;
    this.salary = salary;
    this.roleCode = roleCode;
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

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public int getSalary() {
    return salary;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Employee{");
    if (code != null) sb.append("code='").append(code).append('\'');
    if (name != null) sb.append(", name='").append(name).append('\'');
    if (manager != null) sb.append(", manager='").append(manager).append('\'');
    if (salary != -1) sb.append(", salary=").append(salary);
    if (roleCode != null) sb.append(", roleCode='").append(roleCode).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
