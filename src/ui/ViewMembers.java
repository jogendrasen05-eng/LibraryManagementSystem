package ui;

import dao.MemberDAO;
import model.Member;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewMembers extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnRefresh;
    private JButton btnBack;

    public ViewMembers() {

        setTitle("View Members");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("VIEW MEMBERS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Member ID");
        model.addColumn("Member Name");
        model.addColumn("Phone");
        model.addColumn("Email");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        btnBack = new JButton("Back");

        bottom.add(btnUpdate);
        bottom.add(btnDelete);
        bottom.add(btnRefresh);
        bottom.add(btnBack);

        panel.add(bottom, BorderLayout.SOUTH);

        add(panel);

        loadMembers();

        btnRefresh.addActionListener(e -> loadMembers());

        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });

        btnUpdate.addActionListener(e -> updateMember());

        btnDelete.addActionListener(e -> deleteMember());
    }

    private void loadMembers() {

        model.setRowCount(0);

        MemberDAO dao = new MemberDAO();

        ArrayList<Member> list = dao.getAllMembers();

        for (Member member : list) {

            model.addRow(new Object[]{
                member.getMemberId(),
                member.getMemberName(),
                member.getPhone(),
                member.getEmail()
            });

        }

    }

    private void updateMember() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a member.");
            return;
        }

        try {

            Member member = new Member();

            member.setMemberId(Integer.parseInt(model.getValueAt(row, 0).toString()));

            member.setMemberName(JOptionPane.showInputDialog(
                    this,
                    "Member Name",
                    model.getValueAt(row, 1)));

            member.setPhone(JOptionPane.showInputDialog(
                    this,
                    "Phone",
                    model.getValueAt(row, 2)));

            member.setEmail(JOptionPane.showInputDialog(
                    this,
                    "Email",
                    model.getValueAt(row, 3)));

            MemberDAO dao = new MemberDAO();

            if (dao.updateMember(member)) {

                JOptionPane.showMessageDialog(this,
                        "Member Updated Successfully!");

                loadMembers();

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());

        }

    }

    private void deleteMember() {

        int row = table.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(this,
                    "Please select a member.");

            return;

        }

        int memberId = Integer.parseInt(
                model.getValueAt(row, 0).toString());

        int option = JOptionPane.showConfirmDialog(
                this,
                "Delete this member?",
                "Confirm",
                JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {

            MemberDAO dao = new MemberDAO();

            if (dao.deleteMember(memberId)) {

                JOptionPane.showMessageDialog(this,
                        "Member Deleted Successfully!");

                loadMembers();

            }

        }

    }

}