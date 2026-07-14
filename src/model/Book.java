package model;

public class Book {

    private int bookId;
    private String bookName;
    private int authorId;
    private double price;
    private String status;

    public Book() {
    }

    public Book(int bookId, String bookName, int authorId, double price, String status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorId = authorId;
        this.price = price;
        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}