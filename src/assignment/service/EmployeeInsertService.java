package assignment.service;

import assignment.Employee;

import java.util.List;

public class EmployeeInsertService {

  final private String targetTable = "employee";

  public void insertEmployees(List<Employee> employees) {
    String query = "insert into employee values (?, ?, ?, ?, ?)";
    DatabaseService.executeInsertQuery(query, preparedStatement -> {
      try {
        for (Employee employee : employees) {
          preparedStatement.setString(1, employee.getCode());
          preparedStatement.setString(2, employee.getName());
          preparedStatement.setString(3, employee.getManager());
          preparedStatement.setInt(4, employee.getSalary());
          preparedStatement.setString(5, employee.getRoleCode());

          preparedStatement.addBatch();
          preparedStatement.clearParameters();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

}
