package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Book;

public class BookDAO {

    // ==================== Add Book ====================
    public boolean addBook(Book book) {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO BOOK (BOOK_ID, BOOK_NAME, AUTHOR_ID, PRICE, STATUS) VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setInt(3, book.getAuthorId());
            ps.setDouble(4, book.getPrice());
            ps.setString(5, book.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    // ==================== View Books ====================
    public ArrayList<Book> getAllBooks() {

        ArrayList<Book> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM BOOK ORDER BY BOOK_ID";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getInt("BOOK_ID"));
                book.setBookName(rs.getString("BOOK_NAME"));
                book.setAuthorId(rs.getInt("AUTHOR_ID"));
                book.setPrice(rs.getDouble("PRICE"));
                book.setStatus(rs.getString("STATUS"));

                list.add(book);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;

    }

    // ==================== Update Book ====================
    public boolean updateBook(Book book) {

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE BOOK SET BOOK_NAME=?, AUTHOR_ID=?, PRICE=?, STATUS=? WHERE BOOK_ID=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, book.getBookName());
            ps.setInt(2, book.getAuthorId());
            ps.setDouble(3, book.getPrice());
            ps.setString(4, book.getStatus());
            ps.setInt(5, book.getBookId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    // ==================== Delete Book ====================
    public boolean deleteBook(int bookId) {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM BOOK WHERE BOOK_ID=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    // ==================== Search Book ====================
    public Book searchBook(int bookId) {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM BOOK WHERE BOOK_ID=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Book book = new Book();

                book.setBookId(rs.getInt("BOOK_ID"));
                book.setBookName(rs.getString("BOOK_NAME"));
                book.setAuthorId(rs.getInt("AUTHOR_ID"));
                book.setPrice(rs.getDouble("PRICE"));
                book.setStatus(rs.getString("STATUS"));

                return book;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

}