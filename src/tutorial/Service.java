import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Service {

  protected void closeConnection(Connection connection, PreparedStatement preparedStatement) {
    if (connection != null) {
      try {
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  protected void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
    closeConnection(connection, preparedStatement);
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  protected void printSQLException(SQLException exception) {
    System.out.println("SQLException: " + exception.getMessage());
    System.out.println("SQLState: " + exception.getSQLState());
  }

  protected void printAllStudent(ResultSet resultSet) {
    try {
      while (resultSet.next()) {
        System.out.print("[학번] " + resultSet.getString("id") + " || ");
        System.out.print("[이름] " + resultSet.getString("name") + " || ");
        System.out.println("[생일] " + resultSet.getString("birthday"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
