package ui;

import database.DBConnection;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin, btnExit;

    public LoginFrame() {

        setTitle("Library Management System");
        setSize(500, 320);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        JLabel lblTitle = new JLabel("LIBRARY MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setBounds(45, 20, 420, 30);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(60, 80, 100, 25);

        txtUsername = new JTextField();
        txtUsername.setBounds(170, 80, 220, 30);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(60, 130, 100, 25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(170, 130, 220, 30);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(100, 210, 100, 35);

        btnExit = new JButton("Exit");
        btnExit.setBounds(260, 210, 100, 35);

        panel.add(lblTitle);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnExit);

        add(panel);

        btnLogin.addActionListener(e -> login());

        btnExit.addActionListener(e -> System.exit(0));
    }

    private void login() {

        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        if (username.equals("library") && password.equals("library123")) {

            Connection con = DBConnection.getConnection();

            if (con != null) {

                JOptionPane.showMessageDialog(this,
                        "Login Successful!");

                dispose();

                new Dashboard().setVisible(true);

            } else {

                JOptionPane.showMessageDialog(this,
                        "Database Connection Failed!");

            }

        } else {

            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password!");

        }

    }
}