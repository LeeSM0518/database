package assignment.service.selectservice;

import assignment.Employee;
import assignment.service.DatabaseService;

import java.util.*;

public class EmployeeSelectService {

  public static final String EMP_CODE = "emp_code";
  public static final String EMP_NAME = "emp_name";
  public static final String EMP_MGT = "emp_mgt";
  public static final String EMP_SAL = "emp_sal";
  public static final String EMP_RCODE = "emp_rcode";

  private SelectService selectService = new SelectService("employee");

  public void printNotAll(String... selectList) {
    selectService.notAll(resultSet -> {
      try {
        while (resultSet.next()) {
          for (String str : selectList) {
            if (!str.equals(EMP_SAL))
              System.out.println("[" + str + "] " + resultSet.getString(str));
            else
              System.out.println("[" + str + "] " + resultSet.getInt(str));
          }
          System.out.println("-----------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, selectList);
  }

  public void printSumAndAvg(String target) {
    selectService.sumAndAvg(resultSet -> {
      try {
        while (resultSet.next()) {
          System.out.println("[" + target + "] Sum = " + resultSet.getInt(1));
          System.out.println("[" + target + "] Avg = " + resultSet.getDouble(2));
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, target);
  }

  public void printNameAndManager() {
    String[] name = {"e1.emp_name", "e2.emp_name"};
    String[] from = {"employee e1", "employee e2"};
    String[] where = {"e1.emp_mgt = e2.emp_code"};

    selectService.innerJoin(resultSet -> {
      try {
        while (resultSet.next()) {
          System.out.println("[이름] : " + resultSet.getString(1));
          System.out.println("[관리자] : " + resultSet.getString(2));
          System.out.println("--------------------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, name, from, where);
  }

  public void printOrderBySalaryAndName() {
    List<String> orderByList = Arrays.asList(EMP_NAME, EMP_SAL + " DESC");
    selectService.orderBy(resultSet -> {
      try {
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
          employees.add(new Employee(null, resultSet.getString(EMP_NAME),
              null, resultSet.getInt(EMP_SAL), null));
        }
        employees.forEach(System.out::println);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, new String[]{EMP_NAME, EMP_SAL}, orderByList);
  }

  public void printEmployeeBySalary() {
    String query = "select emp_rcode, round(avg(emp_sal), 2), min(emp_sal), max(emp_sal) "
        + "from employee "
        + "group by emp_rcode "
        + "having 5000 <= avg(emp_sal) "
        + "order by emp_rcode";
    DatabaseService.executeSelectQuery(query, resultSet -> {
      System.out.println(query);
      try {
        while (resultSet.next()) {
          System.out.println("[직급 코드] : " + resultSet.getString(1));
          System.out.println("[평균 급여액] : " + resultSet.getDouble(2));
          System.out.println("[최소 급여액] : " + resultSet.getInt(3));
          System.out.println("[최대 급여액] : " + resultSet.getInt(4));
          System.out.println("---------------------------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  public void printByOverAvgSalary() {
    String query = "select emp_name, emp_sal " +
        "from employee " +
        "where emp_sal > (" +
        "select avg(emp_sal) from employee)";
    DatabaseService.executeSelectQuery(query, resultSet -> {
      try {
        while (resultSet.next()) {
          System.out.println("[이름] : " + resultSet.getString(1));
          System.out.println("[급여] : " + resultSet.getInt(2));
          System.out.println("---------------------------------------------------------------");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

}
