package assignment.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookDatabase {

  private static Connection connection = null;
  private List<Book> initBooks = new ArrayList<>();

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

  public Book[] getDBBooks() {
    String query = "select * from book";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
      while (rs.next()) {
        initBooks.add(new Book(
            rs.getString("name"),
            rs.getString("price"),
            rs.getString("author")));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("initbooks");
    for (Book book : initBooks) System.out.println(book.getBookName());

    return initBooks.toArray(new Book[0]);
  }

  public void saveBooks(final Book[] books) {
    addBooks(books);
    removeBooks(books);
  }

  private void addBooks(final Book[] books) {
    List<Book> addBooks = Stream.of(books)
        .filter(book -> !initBooks.contains(book))
        .collect(Collectors.toList());

    String query = "insert into book (name, price, author) values" +
        " (?, ?, ?)";

    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
      for (Book book : addBooks) {
        ps.setString(1, book.getBookName());
        ps.setString(2, book.getBookPrice());
        ps.setString(3, book.getBookAuthor());
        ps.addBatch();
        ps.clearParameters();
      }
      ps.executeBatch();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("addbooks1");
    for (Book book : books) System.out.println(book.getBookName());
    System.out.println("addbooks2");
    for (Book book : addBooks) System.out.println(book.getBookName());
  }

  private void removeBooks(final Book[] books) {
    List<Book> currentBooks = Arrays.asList(books);
    List<Book> removeBooks = initBooks.stream()
        .filter(book -> !currentBooks.contains(book))
        .collect(Collectors.toList());

    String query = "delete from book where name=? and author=?";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
      for (Book book : removeBooks) {
        ps.setString(1, book.getBookName());
        ps.setString(2, book.getBookAuthor());
        ps.addBatch();
        ps.clearParameters();
      }
      ps.executeBatch();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("removeBooks1");
    for (Book book : books) System.out.println(book.getBookName());
    System.out.println("removeBooks2");
    for (Book book : removeBooks) System.out.println(book.getBookName());
  }

  public void removeAllBooks() {
    Scanner sc = new Scanner(System.in);

    System.out.println();
    System.out.print("정말 책 정보를 모두 지우시나요?(YES, NO)");
    System.out.print(">> ");
    String in = sc.nextLine();
    if (in.equals("NO")) return;

    String query = "delete from book";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(query);) {
      ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
