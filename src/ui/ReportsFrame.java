package ui;

import dao.ReportDAO;
import java.awt.*;
import javax.swing.*;

public class ReportsFrame extends JFrame {

    JLabel lblBooks;
    JLabel lblMembers;
    JLabel lblIssued;
    JLabel lblAvailable;

    JButton btnRefresh;
    JButton btnBack;

    public ReportsFrame() {

        setTitle("Library Reports");

        setSize(500,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("LIBRARY REPORTS");

        title.setFont(new Font("Arial",Font.BOLD,24));

        title.setBounds(120,20,300,30);

        lblBooks = new JLabel();

        lblBooks.setBounds(60,80,350,30);

        lblMembers = new JLabel();

        lblMembers.setBounds(60,120,350,30);

        lblIssued = new JLabel();

        lblIssued.setBounds(60,160,350,30);

        lblAvailable = new JLabel();

        lblAvailable.setBounds(60,200,350,30);

        btnRefresh = new JButton("Refresh");

        btnRefresh.setBounds(70,260,120,35);

        btnBack = new JButton("Back");

        btnBack.setBounds(270,260,120,35);

        panel.add(title);

        panel.add(lblBooks);

        panel.add(lblMembers);

        panel.add(lblIssued);

        panel.add(lblAvailable);

        panel.add(btnRefresh);

        panel.add(btnBack);

        add(panel);

        loadReport();

        btnRefresh.addActionListener(e -> loadReport());

        btnBack.addActionListener(e -> {

            dispose();

            new Dashboard().setVisible(true);

        });

    }

    private void loadReport() {

        ReportDAO dao = new ReportDAO();

        lblBooks.setText("📚 Total Books : " + dao.getTotalBooks());

        lblMembers.setText("👤 Total Members : " + dao.getTotalMembers());

        lblIssued.setText("📖 Issued Books : " + dao.getIssuedBooks());

        lblAvailable.setText("✅ Available Books : " + dao.getAvailableBooks());

    }

}