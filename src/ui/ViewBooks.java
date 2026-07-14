package ui;

import dao.BookDAO;
import model.Book;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class ViewBooks extends JFrame {

    private JButton btnUpdate;
    private JButton btnDelete;
    private JTable table;
    private DefaultTableModel model;

    private JButton btnRefresh;
    private JButton btnBack;

    public ViewBooks() {

        setTitle("View Books");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("VIEW BOOKS", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(title, BorderLayout.NORTH);

        model = new DefaultTableModel();

        model.addColumn("Book ID");
        model.addColumn("Book Name");
        model.addColumn("Author ID");
        model.addColumn("Price");
        model.addColumn("Status");

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

        loadBooks();

        btnRefresh.addActionListener(e -> loadBooks());

        btnBack.addActionListener(e -> {
            dispose();
            new Dashboard().setVisible(true);
        });
        btnUpdate.addActionListener(e -> updateBook());

        btnDelete.addActionListener(e -> deleteBook());
    }
    

    private void loadBooks() {

        model.setRowCount(0);

        BookDAO dao = new BookDAO();

        ArrayList<Book> books = dao.getAllBooks();

        for (Book book : books) {

            model.addRow(new Object[]{
                book.getBookId(),
                book.getBookName(),
                book.getAuthorId(),
                book.getPrice(),
                book.getStatus()
            });

        }

    }
    private void updateBook() {

    int row = table.getSelectedRow();

    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a book.");
        return;
    }

    try {

        Book book = new Book();

        book.setBookId(Integer.parseInt(model.getValueAt(row, 0).toString()));

        book.setBookName(JOptionPane.showInputDialog(
                this,
                "Book Name",
                model.getValueAt(row, 1)));

        book.setAuthorId(Integer.parseInt(JOptionPane.showInputDialog(
                this,
                "Author ID",
                model.getValueAt(row, 2))));

        book.setPrice(Double.parseDouble(JOptionPane.showInputDialog(
                this,
                "Price",
                model.getValueAt(row, 3))));

        book.setStatus(JOptionPane.showInputDialog(
                this,
                "Status",
                model.getValueAt(row, 4)));

        BookDAO dao = new BookDAO();

        if (dao.updateBook(book)) {
            JOptionPane.showMessageDialog(this, "Book Updated Successfully!");
            loadBooks();
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage());
    }
}

    
    private void deleteBook() {

    int row = table.getSelectedRow();

    if (row == -1) {

        JOptionPane.showMessageDialog(this,
                "Please select a book.");

        return;
    }

    int bookId = Integer.parseInt(
            model.getValueAt(row, 0).toString());

    int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this book?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

    if (option == JOptionPane.YES_OPTION) {

        BookDAO dao = new BookDAO();

        if (dao.deleteBook(bookId)) {

            JOptionPane.showMessageDialog(this,
                    "Book Deleted Successfully!");

            loadBooks();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Failed to Delete Book!");

        }

    }

}
}