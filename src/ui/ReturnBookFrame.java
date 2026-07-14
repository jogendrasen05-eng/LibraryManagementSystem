package ui;

import dao.ReturnBookDAO;

import java.awt.*;
import javax.swing.*;

public class ReturnBookFrame extends JFrame {

    JTextField txtIssueId;

    JButton btnReturn;
    JButton btnBack;

    public ReturnBookFrame() {

        setTitle("Return Book");
        setSize(400,220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("RETURN BOOK");

        title.setFont(new Font("Arial",Font.BOLD,22));

        title.setBounds(100,20,220,30);

        JLabel lblIssue = new JLabel("Issue ID");

        lblIssue.setBounds(40,80,100,25);

        txtIssueId = new JTextField();

        txtIssueId.setBounds(140,80,180,30);

        btnReturn = new JButton("Return");

        btnReturn.setBounds(40,140,120,35);

        btnBack = new JButton("Back");

        btnBack.setBounds(200,140,120,35);

        panel.add(title);

        panel.add(lblIssue);

        panel.add(txtIssueId);

        panel.add(btnReturn);

        panel.add(btnBack);

        add(panel);

        btnReturn.addActionListener(e -> {

            try {

                int issueId =
                        Integer.parseInt(txtIssueId.getText());

                ReturnBookDAO dao =
                        new ReturnBookDAO();

                if (dao.returnBook(issueId)) {

                    JOptionPane.showMessageDialog(this,
                            "Book Returned Successfully!");

                    txtIssueId.setText("");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Invalid Issue ID!");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            }

        });

        btnBack.addActionListener(e -> {

            dispose();

            new Dashboard().setVisible(true);

        });

    }

}