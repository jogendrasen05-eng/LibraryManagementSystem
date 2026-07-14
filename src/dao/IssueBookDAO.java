package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.IssueBook;

public class IssueBookDAO {

    public boolean issueBook(IssueBook issue) {

        Connection con = DBConnection.getConnection();

        String sql1 = "INSERT INTO ISSUEBOOK (ISSUE_ID, BOOK_ID, MEMBER_ID, ISSUE_DATE, RETURN_DATE) VALUES (?, ?, ?, SYSDATE, NULL)";
        String sql2 = "UPDATE BOOK SET STATUS='Issued' WHERE BOOK_ID=?";

        try {

            con.setAutoCommit(false);

            PreparedStatement ps1 = con.prepareStatement(sql1);

            ps1.setInt(1, issue.getIssueId());
            ps1.setInt(2, issue.getBookId());
            ps1.setInt(3, issue.getMemberId());

            ps1.executeUpdate();

            PreparedStatement ps2 = con.prepareStatement(sql2);

            ps2.setInt(1, issue.getBookId());

            ps2.executeUpdate();

            con.commit();

            return true;

        } catch (SQLException e) {

    try {
        con.rollback();
    } catch (SQLException ex) {
    }

    if (e.getErrorCode() == 1) {
        System.out.println("Issue ID already exists.");
    } else {
        e.printStackTrace();
    }

    return false;
}
    }

}