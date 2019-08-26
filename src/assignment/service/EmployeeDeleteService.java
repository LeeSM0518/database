package assignment.service;

public class EmployeeDeleteService {

  final private String targetTable = "employee";

  public void deleteEmployee(String name) {
    String query = "delete from " + targetTable + " where emp_name = '" + name + "'";
    DatabaseService.executeDeleteQuery(query);
  }

}
