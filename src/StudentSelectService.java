import java.sql.*;

public class StudentSelectService extends Service {

  public void getStudentAll() {
    final String query = "select * from student";
    try (Connection connection = PostgresqlAccess.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery()){

      printAllStudent(resultSet);

    } catch (SQLException sqex) {
      printSQLException(sqex);
    }
  }

  public void getStudentByNo(int studentNo) {
    final String query = "select * from student where id = ?";

    try (Connection connection = PostgresqlAccess.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ){
      preparedStatement.setInt(1, studentNo);
      try (ResultSet resultSet = preparedStatement.executeQuery()) {
        printAllStudent(resultSet);
      }
    } catch (SQLException sqex) {
      printSQLException(sqex);
    }
  }

  public void getStudentByName(String studentName) {
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "select * from student where name = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, studentName);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        System.out.println("[학번] " + resultSet.getString("id") + " || ");
        System.out.println("[이름] " + resultSet.getString("name") + " || ");
        System.out.println("[생일] " + resultSet.getString("birthday"));
      }

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
    } finally {
      closeConnection(connection, preparedStatement, resultSet);
    }
  }

  public void getStudentByBirthday(String studentBirthday) {
    final String query = "select * from student where birthday = '" + studentBirthday + "'";
    try (Connection connection = PostgresqlAccess.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery();
    ) {

      printAllStudent(resultSet);

    } catch (SQLException e) {
      printSQLException(e);
    }

  }

}
