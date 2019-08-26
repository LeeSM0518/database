package assignment.service;

import java.sql.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class DatabaseService {

  private static Connection connection = null;

  private static Connection getConnection() {
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

  public static void executeSelectQuery(String query, Consumer<ResultSet> consumer) {
    try (Connection connection = DatabaseService.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
         ResultSet resultSet = preparedStatement.executeQuery();
    ) {
      consumer.accept(resultSet);
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  public static void executeUpdateQuery(String query) {
    try (Connection connection = DatabaseService.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      connection.setAutoCommit(false);
      int retValue = preparedStatement.executeUpdate();
      connection.commit();

      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  public static void executeInsertQuery(String query, Consumer<PreparedStatement> consumer) {
    try (Connection connection = DatabaseService.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      connection.setAutoCommit(false);
      consumer.accept(preparedStatement);
      int[] retValue = preparedStatement.executeBatch();
      connection.commit();
      System.out.println(retValue.length + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  public static void executeDeleteQuery(String query) {
    try (Connection connection = DatabaseService.getConnection();
         PreparedStatement preparedStatement = connection.prepareStatement(query);
    ) {
      connection.setAutoCommit(false);
      int retValue = preparedStatement.executeUpdate();
      connection.commit();
      System.out.println(retValue + "건의 사항이 처리되었습니다.");
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  public static void printSQLException(SQLException exception) {
    System.out.println("SQLException: " + exception.getMessage());
    System.out.println("SQLState: " + exception.getSQLState());
    System.out.println(Arrays.toString(exception.getStackTrace()));
  }

}
