import java.sql.*;
import java.util.Scanner;

public class SampleCode {
  public static void main(final String... args) {
    try {
      Connection conn = DriverManager.getConnection
          // jdbc:postgresql://hostname:port/dbname
          ("jdbc:postgresql://localhost:5432/exercise", "scott", "tiger");

      // preparedStatement 를 쓰도록한다.
      Statement stmt;
      stmt = conn.createStatement();
      ResultSet rs;
      // execute : 모든 쿼리 사용 가능
      // Return : Boolean Value
      // executeQuery : 주로 SELECT 사용
      // return - resultSet
      // executeUpdate : 주로 DDL, DML에 사용
      // return - 실행된 행 개수
      rs = stmt.executeQuery("SELECT * FROM STUDENT");
      if (stmt.execute("SELECT * FROM STUDENT")) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        System.out.print("[학번] " + rs.getString(1) + " || ");
        System.out.print("[이름] " + rs.getString(2) + " || ");
        System.out.println("[생일] " + rs.getString(3));
      }
      rs.close();
      stmt.close();
      conn.close();
    } catch (SQLException sqex) {
      System.out.println("SQLException: " + sqex.getMessage());
      System.out.println("SQLState: " + sqex.getSQLState());
    }
  }
}