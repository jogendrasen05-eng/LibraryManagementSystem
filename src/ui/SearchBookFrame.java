package ui;

import dao.BookDAO;
import model.Book;

import java.awt.*;
import javax.swing.*;

public class SearchBookFrame extends JFrame {

    JTextField txtBookId;

    JTextArea result;

    JButton btnSearch, btnBack;

    public SearchBookFrame() {

        setTitle("Search Book");

        setSize(500,400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(null);

        JLabel title = new JLabel("SEARCH BOOK");

        title.setFont(new Font("Arial",Font.BOLD,22));

        title.setBounds(150,20,220,30);

        JLabel lbl = new JLabel("Book ID");

        lbl.setBounds(40,80,100,25);

        txtBookId = new JTextField();

        txtBookId.setBounds(140,80,200,30);

        btnSearch = new JButton("Search");

        btnSearch.setBounds(350,80,100,30);

        result = new JTextArea();

        result.setEditable(false);

        JScrollPane sp = new JScrollPane(result);

        sp.setBounds(40,140,410,150);

        btnBack = new JButton("Back");

        btnBack.setBounds(170,310,120,35);

        panel.add(title);

        panel.add(lbl);

        panel.add(txtBookId);

        panel.add(btnSearch);

        panel.add(sp);

        panel.add(btnBack);

        add(panel);

        btnSearch.addActionListener(e -> {

            BookDAO dao = new BookDAO();

            Book book =
                    dao.searchBook(
                            Integer.parseInt(
                                    txtBookId.getText()));

            if(book==null){

                result.setText("Book Not Found.");

            }else{

                result.setText(
                        "Book ID : "+book.getBookId()+
                        "\nBook Name : "+book.getBookName()+
                        "\nAuthor ID : "+book.getAuthorId()+
                        "\nPrice : "+book.getPrice()+
                        "\nStatus : "+book.getStatus());

            }

        });

        btnBack.addActionListener(e->{

            dispose();

            new Dashboard().setVisible(true);

        });

    }

}