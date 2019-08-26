package tutorial;

import java.sql.*;

public class StudentInsertService extends Service {

  public void insertStudent(Student student) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "insert into student(id, name, birthday) values (?, ?, ?)";

      preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, student.getId());
      preparedStatement.setString(2, student.getName());

      if (student.getBirthday() == null) {
        preparedStatement.setNull(3, Types.DATE);
      } else {
        preparedStatement.setDate(3, student.getBirthday());
      }

      int retValue = preparedStatement.executeUpdate();
      connection.commit();

      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      try {
        if (connection != null) {
          connection.rollback();
        }
      } catch (SQLException ex) {
        e.printStackTrace();
      }
      printSQLException(e);
    } finally {
      closeConnection(connection, preparedStatement);
    }
  }


  public void insertStudentMulti(Student[] students) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      int retValue = 0;
      String query = "insert into student (id, name, birthday) values (?, ?, ?)";
      preparedStatement = connection.prepareStatement(query);

      for (int i = 0; i < students.length; i++) {
        if (students[i].getId() == 0 && students[i].getName() == null) break;

        preparedStatement.setInt(1, students[i].getId());
        preparedStatement.setString(2, students[i].getName());

        if (students[i].getBirthday() == null) {
          preparedStatement.setNull(3, Types.DATE);
        } else {
          preparedStatement.setDate(3, students[i].getBirthday());
        }
        retValue += preparedStatement.executeUpdate();
        preparedStatement.clearParameters();
      }
      connection.commit();
      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      try {
        if (connection != null) {
          connection.rollback();
        }
      } catch (SQLException ex) {
        printSQLException(ex);
      }
      printSQLException(e);
    } finally {
      closeConnection(connection, preparedStatement);
    }
  }



  public void insertStudentMultiBatch(Student[] students) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = PostgresqlAccess.getConnection();
      connection.setAutoCommit(false);

      String query = "insert into student (id, name, birthday) values (?, ?, ?)";
      preparedStatement = connection.prepareStatement(query);

      for (int i = 0; i < students.length; i++) {
        if (students[i].getId() == 0 && students[i].getName() == null) break;

        preparedStatement.setInt(1, students[i].getId());
        preparedStatement.setString(2, students[i].getName());

        if (students[i].getBirthday() == null) {
          preparedStatement.setNull(3, Types.DATE);
        } else {
          preparedStatement.setDate(3, students[i].getBirthday());
        }

        preparedStatement.addBatch();
        preparedStatement.clearParameters();
      }

      int[] retValue = preparedStatement.executeBatch();
      connection.commit();
      System.out.println(retValue.length + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      try {
        if (connection != null) connection.rollback();
      } catch (SQLException ex) {
        printSQLException(ex);
      }
      printSQLException(e);
    } finally {
      closeConnection(connection, preparedStatement);
    }
  }

}
