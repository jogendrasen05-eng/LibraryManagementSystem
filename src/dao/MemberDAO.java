package dao;

import database.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import model.Member;
import java.sql.ResultSet;
public class MemberDAO {

    // Add Member
    public boolean addMember(Member member) {

        Connection con = DBConnection.getConnection();

        String sql = "INSERT INTO MEMBER (MEMBER_ID, MEMBER_NAME, PHONE, EMAIL) VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, member.getMemberId());
            ps.setString(2, member.getMemberName());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getEmail());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    // View Members
    public ArrayList<Member> getAllMembers() {

        ArrayList<Member> list = new ArrayList<>();

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM MEMBER ORDER BY MEMBER_ID";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Member member = new Member();

                member.setMemberId(rs.getInt("MEMBER_ID"));
                member.setMemberName(rs.getString("MEMBER_NAME"));
                member.setPhone(rs.getString("PHONE"));
                member.setEmail(rs.getString("EMAIL"));

                list.add(member);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return list;

    }

    // Search Member
    public Member searchMember(int memberId) {

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, memberId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Member member = new Member();

                member.setMemberId(rs.getInt("MEMBER_ID"));
                member.setMemberName(rs.getString("MEMBER_NAME"));
                member.setPhone(rs.getString("PHONE"));
                member.setEmail(rs.getString("EMAIL"));

                return member;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // Update Member
    public boolean updateMember(Member member) {

        Connection con = DBConnection.getConnection();

        String sql = "UPDATE MEMBER SET MEMBER_NAME=?, PHONE=?, EMAIL=? WHERE MEMBER_ID=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, member.getMemberName());
            ps.setString(2, member.getPhone());
            ps.setString(3, member.getEmail());
            ps.setInt(4, member.getMemberId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

    // Delete Member
    public boolean deleteMember(int memberId) {

        Connection con = DBConnection.getConnection();

        String sql = "DELETE FROM MEMBER WHERE MEMBER_ID=?";

        try {

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, memberId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;

        }

    }

}