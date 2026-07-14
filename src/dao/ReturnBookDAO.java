package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBookDAO {

    public boolean returnBook(int issueId) {

        Connection con = DBConnection.getConnection();

        try {

            con.setAutoCommit(false);

            String getBook =
                    "SELECT BOOK_ID FROM ISSUEBOOK WHERE ISSUE_ID=?";

            PreparedStatement ps1 =
                    con.prepareStatement(getBook);

            ps1.setInt(1, issueId);

            ResultSet rs = ps1.executeQuery();

            if (!rs.next()) {

                return false;

            }

            int bookId = rs.getInt("BOOK_ID");

            String updateIssue =
                    "UPDATE ISSUEBOOK SET RETURN_DATE=SYSDATE WHERE ISSUE_ID=?";

            PreparedStatement ps2 =
                    con.prepareStatement(updateIssue);

            ps2.setInt(1, issueId);

            ps2.executeUpdate();

            String updateBook =
                    "UPDATE BOOK SET STATUS='Available' WHERE BOOK_ID=?";

            PreparedStatement ps3 =
                    con.prepareStatement(updateBook);

            ps3.setInt(1, bookId);

            ps3.executeUpdate();

            con.commit();

            return true;

        } catch (SQLException e) {

            try {
                con.rollback();
            } catch (SQLException ex) {
            }

            e.printStackTrace();

            return false;

        }

    }

}