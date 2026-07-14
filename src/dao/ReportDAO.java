package dao;

import database.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportDAO {

    public int getTotalBooks() {

        return getCount("SELECT COUNT(*) FROM BOOK");

    }

    public int getTotalMembers() {

        return getCount("SELECT COUNT(*) FROM MEMBER");

    }

    public int getIssuedBooks() {

        return getCount("SELECT COUNT(*) FROM BOOK WHERE STATUS='Issued'");

    }

    public int getAvailableBooks() {

        return getCount("SELECT COUNT(*) FROM BOOK WHERE STATUS='Available'");

    }

    private int getCount(String sql) {

        int count = 0;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                count = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;

    }

}