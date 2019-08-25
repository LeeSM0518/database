package assignment.service;

public class EmployeeSelectService {

  private static final String EMP_CODE = "emp_code";
  private static final String EMP_NAME = "emp_name";
  private static final String EMP_MGT = "emp_mgt";
  private static final String EMP_SAL = "emp_sal";
  private static final String EMP_RCODE = "emp_rcode";

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

  public static void main(String[] args) {
    EmployeeSelectService employeeSelectService = new EmployeeSelectService();

    employeeSelectService.printNotAll(EMP_CODE, EMP_NAME, EMP_MGT, EMP_SAL);
  }

}
