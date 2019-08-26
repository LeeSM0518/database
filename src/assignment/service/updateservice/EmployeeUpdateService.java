package assignment.service.updateservice;

import assignment.service.DatabaseService;

public class EmployeeUpdateService {

  final private String targetTable = "employee";

  public void updateSalaryAndRole(String name, String role, double salary) {
    String query = "update " + targetTable + " set emp_rcode = ("
        + "select emp_rcode from emp_role where emp_rname = '" + role + "'), "
        + "emp_sal = emp_sal * " + salary
        + " where emp_name = '" + name + "'";
    DatabaseService.executeUpdateQuery(query);
  }

}
