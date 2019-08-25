package assignment.service;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentSelectService {

  private final int DEPT_CODE = 1;
  private final int DEPT_NAME = 2;
  private final int DEPT_LOC = 3;

  private SelectService selectService = new SelectService("department");

  public void printAll() {
    selectService.all(resultSet -> {
      try {
        while (resultSet.next()) {
          System.out.println("[부서코드] " + resultSet.getString(DEPT_CODE));
          System.out.println("[부서이름] " + resultSet.getString(DEPT_NAME));
          System.out.println("[부서위치] " + resultSet.getString(DEPT_LOC));
          System.out.println("-----------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public static void main(String[] args) {
    DepartmentSelectService service = new DepartmentSelectService();
    service.printAll();
  }

}
