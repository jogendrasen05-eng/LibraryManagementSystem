package ui;

import java.awt.*;
import javax.swing.*;

import dao.BookDAO;
import model.Book;

public class AddBook extends JFrame {

    private JTextField txtBookId;
    private JTextField txtBookName;
    private JTextField txtAuthorId;
    private JTextField txtPrice;
    private JComboBox<String> cmbStatus;

    private JButton btnSave;
    private JButton btnReset;
    private JButton btnBack;

    public AddBook() {

        setTitle("Add Book");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245,245,245));

        JLabel title = new JLabel("ADD BOOK");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(180,20,200,30);

        JLabel lblBookId = new JLabel("Book ID");
        lblBookId.setBounds(50,80,100,25);

        txtBookId = new JTextField();
        txtBookId.setBounds(170,80,220,30);

        JLabel lblBookName = new JLabel("Book Name");
        lblBookName.setBounds(50,130,100,25);

        txtBookName = new JTextField();
        txtBookName.setBounds(170,130,220,30);

        JLabel lblAuthorId = new JLabel("Author ID");
        lblAuthorId.setBounds(50,180,100,25);

        txtAuthorId = new JTextField();
        txtAuthorId.setBounds(170,180,220,30);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(50,230,100,25);

        txtPrice = new JTextField();
        txtPrice.setBounds(170,230,220,30);

        JLabel lblStatus = new JLabel("Status");
        lblStatus.setBounds(50,280,100,25);

        cmbStatus = new JComboBox<>();
        cmbStatus.addItem("Available");
        cmbStatus.addItem("Issued");
        cmbStatus.setBounds(170,280,220,30);

        btnSave = new JButton("Save");
        btnSave.setBounds(40,350,100,35);

        btnReset = new JButton("Reset");
        btnReset.setBounds(180,350,100,35);

        btnBack = new JButton("Back");
        btnBack.setBounds(320,350,100,35);

        panel.add(title);
        panel.add(lblBookId);
        panel.add(txtBookId);
        panel.add(lblBookName);
        panel.add(txtBookName);
        panel.add(lblAuthorId);
        panel.add(txtAuthorId);
        panel.add(lblPrice);
        panel.add(txtPrice);
        panel.add(lblStatus);
        panel.add(cmbStatus);
        panel.add(btnSave);
        panel.add(btnReset);
        panel.add(btnBack);

        add(panel);

        // Abhi temporary actions
        btnReset.addActionListener(e -> {
            txtBookId.setText("");
            txtBookName.setText("");
            txtAuthorId.setText("");
            txtPrice.setText("");
            cmbStatus.setSelectedIndex(0);
        });

        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        btnSave.addActionListener(e -> saveBook());
    }
    private void saveBook() {

    try {

        Book book = new Book();

        book.setBookId(Integer.parseInt(txtBookId.getText()));
        book.setBookName(txtBookName.getText());
        book.setAuthorId(Integer.parseInt(txtAuthorId.getText()));
        book.setPrice(Double.parseDouble(txtPrice.getText()));
        book.setStatus(cmbStatus.getSelectedItem().toString());

        BookDAO dao = new BookDAO();

        if (dao.addBook(book)) {

            JOptionPane.showMessageDialog(this,
                    "Book Added Successfully!");

            txtBookId.setText("");
            txtBookName.setText("");
            txtAuthorId.setText("");
            txtPrice.setText("");
            cmbStatus.setSelectedIndex(0);

        } else {

            JOptionPane.showMessageDialog(this,
                    "Failed to Add Book!");

        }

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this,
                "Error : " + e.getMessage());

    }

}
}