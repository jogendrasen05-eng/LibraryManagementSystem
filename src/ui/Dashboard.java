package ui;

import java.awt.*;
import javax.swing.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Library Management System - Dashboard");
        setSize(800, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));

        // ================= Title =================

        JLabel title = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(100, 20, 600, 40);
        title.setForeground(new Color(0, 70, 140));
        panel.add(title);

        JLabel welcome = new JLabel("Welcome, Admin");
        welcome.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        welcome.setForeground(Color.DARK_GRAY);
        welcome.setBounds(330, 65, 180, 20);
        panel.add(welcome);

        // ================= Buttons =================

        JButton btnAddBook = new JButton("Add Book");
        JButton btnViewBooks = new JButton("View Books");

        JButton btnAddMember = new JButton("Add Member");
        JButton btnViewMembers = new JButton("View Members");

        JButton btnIssueBook = new JButton("Issue Book");
        JButton btnReturnBook = new JButton("Return Book");

        JButton btnReports = new JButton("Reports");
        JButton btnLogout = new JButton("Logout");

        JButton btnSearchBook = new JButton("Search Book");
        JButton btnSearchMember = new JButton("Search Member");

        JButton[] buttons = {
                btnAddBook,
                btnViewBooks,
                btnAddMember,
                btnViewMembers,
                btnIssueBook,
                btnReturnBook,
                btnReports,
                btnLogout,
                btnSearchBook,
                btnSearchMember
        };

        Font buttonFont = new Font("Segoe UI", Font.BOLD, 16);

        for (JButton btn : buttons) {

            btn.setFont(buttonFont);
            btn.setBackground(new Color(0, 123, 255));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        }

        btnLogout.setBackground(new Color(220, 53, 69));

        // ================= Button Positions =================

        btnAddBook.setBounds(120, 110, 200, 50);
        btnViewBooks.setBounds(470, 110, 200, 50);

        btnAddMember.setBounds(120, 190, 200, 50);
        btnViewMembers.setBounds(470, 190, 200, 50);

        btnIssueBook.setBounds(120, 270, 200, 50);
        btnReturnBook.setBounds(470, 270, 200, 50);

        btnReports.setBounds(120, 350, 200, 50);
        btnLogout.setBounds(470, 350, 200, 50);

        btnSearchBook.setBounds(120, 430, 200, 50);
        btnSearchMember.setBounds(470, 430, 200, 50);

        // ================= Add Buttons =================

        panel.add(btnAddBook);
        panel.add(btnViewBooks);

        panel.add(btnAddMember);
        panel.add(btnViewMembers);

        panel.add(btnIssueBook);
        panel.add(btnReturnBook);

        panel.add(btnReports);
        panel.add(btnLogout);

        panel.add(btnSearchBook);
        panel.add(btnSearchMember);

        // ================= Footer =================

        JLabel footer = new JLabel("© 2026 Jogendra Singh Sen");
        footer.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footer.setForeground(Color.GRAY);
        footer.setBounds(300, 560, 220, 20);

        panel.add(footer);

        add(panel);

        // ================= Add Book =================

        btnAddBook.addActionListener(e -> {

            dispose();
            new AddBook().setVisible(true);

        });

        // ================= View Books =================

        btnViewBooks.addActionListener(e -> {

            dispose();
            new ViewBooks().setVisible(true);

        });

        // ================= Add Member =================

        btnAddMember.addActionListener(e -> {

            dispose();
            new AddMember().setVisible(true);

        });

        // ================= View Members =================

        btnViewMembers.addActionListener(e -> {

            dispose();
            new ViewMembers().setVisible(true);

        });

        // ================= Search Book =================

        btnSearchBook.addActionListener(e -> {

            dispose();
            new SearchBookFrame().setVisible(true);

        });

        // ================= Search Member =================

        btnSearchMember.addActionListener(e -> {

            dispose();
            new SearchMemberFrame().setVisible(true);

        });

        // ================= Issue Book =================

        btnIssueBook.addActionListener(e -> {

            dispose();
            new IssueBookFrame().setVisible(true);

        });

        // ================= Return Book =================

        btnReturnBook.addActionListener(e -> {

            dispose();
            new ReturnBookFrame().setVisible(true);

        });

        // ================= Reports =================

        btnReports.addActionListener(e -> {

            dispose();
            new ReportsFrame().setVisible(true);

        });

        // ================= Logout =================

        btnLogout.addActionListener(e -> {

            int option = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Logout Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {

                dispose();
                new LoginFrame().setVisible(true);

            }

        });

    }

}