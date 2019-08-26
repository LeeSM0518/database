package tutorial;

import java.sql.*;

public class PostgresqlAccess {

  private static Connection connection = null;

  static public void init() {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() {
    String url = "jdbc:postgresql://arjuna.db.elephantsql.com:5432/";
    String username = "fsmfppcj";
    String password = "opXwqwWLpezpFQHX6OWFl3mQW1xf0VqH";

    try {
      connection = DriverManager.getConnection(url, username, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return connection;
  }

  public static void main(String[] args) {
    String query = "select * from employee";
    try(Connection connection = PostgresqlAccess.getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement(query);
        ResultSet resultSet = prepareStatement.executeQuery()) {
      while (resultSet.next()) {
        System.out.println("코드 : " + resultSet.getString("emp_code"));
        System.out.println("이름 : " + resultSet.getString("emp_name"));
        System.out.println("관리자 : " + resultSet.getString("emp_mgt"));
        System.out.println("급여 : " + resultSet.getString("emp_sal"));
        System.out.println("직급 : " + resultSet.getString("emp_rcode"));
      }
    } catch (SQLException e) {
      System.out.println("SQLException : " + e.getMessage());
      System.out.println("SQLState : " + e.getSQLState());
    }
  }

}
