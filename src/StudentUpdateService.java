import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentUpdateService extends Service{

  public void updateStudentBirthday(int id, String birthday) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "update student set birthday = ? where id = ?";
      preparedStatement = connection.prepareStatement(query);

      preparedStatement.setDate(1, Date.valueOf(birthday));
      preparedStatement.setInt(2, id);

      int retValue = preparedStatement.executeUpdate();
      connection.commit();

      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      printSQLException(e);
    } finally {
      closeConnection(connection, preparedStatement);
    }
  }

  public void updateStudentBirthday(Student student) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "update student set birthday = ? where id = ?";
      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setDate(1, student.getBirthday());
      preparedStatement.setInt(2, student.getId());

      int retValue = preparedStatement.executeUpdate();
      connection.commit();

      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      printSQLException(e);
    } finally {
      closeConnection(connection, preparedStatement);
    }
  }

  public void updateStudentBirthdayMultiBatch(Student[] students) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "update student set birthday = ? where id = ?";
      preparedStatement = connection.prepareStatement(query);

      for (Student student : students) {
        if (student.getId() == 0 && student.getBirthday() == null) break;

        preparedStatement.setDate(1, student.getBirthday());
        preparedStatement.setInt(2, student.getId());

        preparedStatement.addBatch();
        preparedStatement.clearParameters();
      }

      int[] retValue = preparedStatement.executeBatch();
      connection.commit();
      System.out.println(retValue.length + "건의 사항이 처리되었습니다.");
    } catch (SQLException sqex) {
      try {
        if (connection != null) connection.rollback();
      } catch (SQLException ex) {
        printSQLException(ex);
      }
      printSQLException(sqex);
    }
  }

}
