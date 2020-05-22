package assignment.service;

import java.util.Arrays;
import java.util.Objects;

public class Book {
  private String bookName;
  private String bookPrice;
  private String bookAuthor;
  private Book[] books;

  public Book(){}

  public Book(String bookName, String bookPrice, String bookAuthor) {
    this.bookName = bookName;
    this.bookPrice = bookPrice;
    this.bookAuthor = bookAuthor;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(bookName, book.bookName) &&
        Objects.equals(bookPrice, book.bookPrice) &&
        Objects.equals(bookAuthor, book.bookAuthor) &&
        Arrays.equals(books, book.books);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(bookName, bookPrice, bookAuthor);
    result = 31 * result + Arrays.hashCode(books);
    return result;
  }

  public Book[] getBook(){
    return books;
  }

  public void setBooks(Book[] books) {
    this.books = books;
  }

  public String getBookName() {
    return bookName;
  }

  public void setBookName(String bookName){
    this.bookName = bookName;
  }

  public String getBookPrice() {
    return bookPrice;
  }

  public void setBookPrice(String bookPrice) {
    this.bookPrice = bookPrice;
  }

  public String getBookAuthor() {
    return bookAuthor;
  }

  public void setBookAuthor(String bookAuthor) {
    this.bookAuthor = bookAuthor;
  }
}