import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDeleteService extends Service {

  public void deleteStudentId(int studentId) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "delete from student where id = ?";
      statement = connection.prepareStatement(query);
      statement.setInt(1, studentId);

      int retValue = statement.executeUpdate();
      connection.commit();

      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException sqex) {
      printSQLException(sqex);
    } finally {
      closeConnection(connection, statement);
    }
  }

  public void deleteStudentIdMultiBatch(Student[] students) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "delete from student where id = ?";
      statement = connection.prepareStatement(query);

      for (Student student : students) {
        if (student.getId() == 0) break;
        statement.setInt(1, student.getId());
        statement.addBatch();
        statement.clearParameters();
      }

      int[] retValue = statement.executeBatch();
      connection.commit();

      System.out.println(retValue.length + "건의 사항이 처리되었습니다.");
    } catch (SQLException sqex) {
      printSQLException(sqex);
    } finally {
      closeConnection(connection, statement);
    }
  }

}
