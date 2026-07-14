package ui;

import dao.MemberDAO;
import model.Member;

import java.awt.*;
import javax.swing.*;

public class SearchMemberFrame extends JFrame {

    private JTextField txtMemberId;
    private JTextArea result;

    private JButton btnSearch;
    private JButton btnBack;

    public SearchMemberFrame() {

        setTitle("Search Member");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("SEARCH MEMBER");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(140, 20, 250, 30);

        JLabel lbl = new JLabel("Member ID");
        lbl.setBounds(40, 80, 100, 25);

        txtMemberId = new JTextField();
        txtMemberId.setBounds(150, 80, 180, 30);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(340, 80, 100, 30);

        result = new JTextArea();
        result.setEditable(false);

        JScrollPane sp = new JScrollPane(result);
        sp.setBounds(40, 140, 400, 150);

        btnBack = new JButton("Back");
        btnBack.setBounds(170, 310, 120, 35);

        panel.add(title);
        panel.add(lbl);
        panel.add(txtMemberId);
        panel.add(btnSearch);
        panel.add(sp);
        panel.add(btnBack);

        add(panel);

        btnSearch.addActionListener(e -> searchMember());

        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });

    }

    private void searchMember() {

        try {

            int memberId = Integer.parseInt(txtMemberId.getText());

            MemberDAO dao = new MemberDAO();

            Member member = dao.searchMember(memberId);

            if (member == null) {

                result.setText("Member Not Found.");

            } else {

                result.setText(
                        "Member ID : " + member.getMemberId()
                        + "\nMember Name : " + member.getMemberName()
                        + "\nPhone : " + member.getPhone()
                        + "\nEmail : " + member.getEmail());

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    "Please enter a valid Member ID.");

        }

    }

}