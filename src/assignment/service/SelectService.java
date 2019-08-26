package assignment.service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

  public void groupBy(Consumer<ResultSet> consumer, String groupBy) {
    String query = "select " + groupBy + " from " + selectTargetTable + " group by " + groupBy;
    DatabaseService.executeSelectQuery(query, consumer);
  }

  public void sumAndAvg(Consumer<ResultSet> consumer, String target) {
    String query = "select sum(" + target + "), round(avg(" + target + "), 2) from " + selectTargetTable;
    DatabaseService.executeSelectQuery(query, consumer);
  }

  public void innerJoin(Consumer<ResultSet> consumer, String[] select, String[] from, String[] where) {
    String query = "select " + String.join(" , ", select)
        + " from " + String.join(" , ", from)
        + " where " + String.join(" and ", where);
    System.out.println(query);
    DatabaseService.executeSelectQuery(query, consumer);
  }

  public void orderBy(Consumer<ResultSet> consumer, String[] select, List<String> orderBy) {
    String query = "select " + String.join(" , ", select)
        + " from " + selectTargetTable
        + " order by " + String.join(" , ", orderBy);
    DatabaseService.executeSelectQuery(query, consumer);
  }

}
