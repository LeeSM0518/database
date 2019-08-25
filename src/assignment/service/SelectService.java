package assignment.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;

public class SelectService {

  private String selectTargetTable;

  public SelectService(String table) {
    this.selectTargetTable = table;
  }

  public void all(Consumer<ResultSet> consumer) {
    String query = "select * from " + selectTargetTable;
    DatabaseService.executeSelectQuery(query, consumer);
  }

  public void notAll(Consumer<ResultSet> consumer, String... selectList) {
    String selectColumn = String.join(" , ", selectList);
    String query = "select " + selectColumn + " from " + selectTargetTable;
    DatabaseService.executeSelectQuery(query, consumer);
  }

  public void includeWhere(Consumer<ResultSet> consumer, String where, String... selectList) {
    String selectColumn = String.join(" , ", selectList);
    String query = "select " + selectColumn + " from " + selectTargetTable + " where " + where;
    DatabaseService.executeSelectQuery(query, consumer);
  }

}
