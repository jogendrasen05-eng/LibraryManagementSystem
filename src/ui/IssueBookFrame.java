package ui;

import dao.IssueBookDAO;
import model.IssueBook;

import java.awt.*;
import javax.swing.*;

public class IssueBookFrame extends JFrame {

    private JTextField txtIssueId;
    private JTextField txtBookId;
    private JTextField txtMemberId;

    private JButton btnIssue;
    private JButton btnReset;
    private JButton btnBack;

    public IssueBookFrame() {

        setTitle("Issue Book");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("ISSUE BOOK");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(130, 20, 220, 30);

        JLabel l1 = new JLabel("Issue ID");
        l1.setBounds(40, 80, 100, 25);

        txtIssueId = new JTextField();
        txtIssueId.setBounds(160, 80, 200, 30);

        JLabel l2 = new JLabel("Book ID");
        l2.setBounds(40, 130, 100, 25);

        txtBookId = new JTextField();
        txtBookId.setBounds(160, 130, 200, 30);

        JLabel l3 = new JLabel("Member ID");
        l3.setBounds(40, 180, 100, 25);

        txtMemberId = new JTextField();
        txtMemberId.setBounds(160, 180, 200, 30);

        btnIssue = new JButton("Issue");
        btnIssue.setBounds(30, 250, 100, 35);

        btnReset = new JButton("Reset");
        btnReset.setBounds(160, 250, 100, 35);

        btnBack = new JButton("Back");
        btnBack.setBounds(290, 250, 100, 35);

        panel.add(title);
        panel.add(l1);
        panel.add(txtIssueId);
        panel.add(l2);
        panel.add(txtBookId);
        panel.add(l3);
        panel.add(txtMemberId);
        panel.add(btnIssue);
        panel.add(btnReset);
        panel.add(btnBack);

        add(panel);

        btnIssue.addActionListener(e -> saveIssue());

        btnReset.addActionListener(e -> {
            txtIssueId.setText("");
            txtBookId.setText("");
            txtMemberId.setText("");
        });

        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
    }

    private void saveIssue() {

        try {

            IssueBook issue = new IssueBook();

            issue.setIssueId(Integer.parseInt(txtIssueId.getText().trim()));
            issue.setBookId(Integer.parseInt(txtBookId.getText().trim()));
            issue.setMemberId(Integer.parseInt(txtMemberId.getText().trim()));

            // DEBUG
            System.out.println("========== DEBUG ==========");
            System.out.println("Issue ID  : " + issue.getIssueId());
            System.out.println("Book ID   : " + issue.getBookId());
            System.out.println("Member ID : " + issue.getMemberId());
            System.out.println("===========================");

            IssueBookDAO dao = new IssueBookDAO();

            if (dao.issueBook(issue)) {

                JOptionPane.showMessageDialog(this,
                        "Book Issued Successfully!");

                txtIssueId.setText("");
                txtBookId.setText("");
                txtMemberId.setText("");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Issue Failed!\nCheck Output Window for details.");

            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric IDs.");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());

            e.printStackTrace();

        }

    }

}