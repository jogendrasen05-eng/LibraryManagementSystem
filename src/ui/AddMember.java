package ui;

import dao.MemberDAO;
import model.Member;

import java.awt.*;
import javax.swing.*;

public class AddMember extends JFrame {

    private JTextField txtMemberId;
    private JTextField txtMemberName;
    private JTextField txtPhone;
    private JTextField txtEmail;

    private JButton btnSave;
    private JButton btnReset;
    private JButton btnBack;

    public AddMember() {

        setTitle("Add Member");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245,245,245));

        JLabel title = new JLabel("ADD MEMBER");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setBounds(160,20,220,30);

        JLabel lblId = new JLabel("Member ID");
        lblId.setBounds(50,80,100,25);

        txtMemberId = new JTextField();
        txtMemberId.setBounds(170,80,220,30);

        JLabel lblName = new JLabel("Member Name");
        lblName.setBounds(50,130,100,25);

        txtMemberName = new JTextField();
        txtMemberName.setBounds(170,130,220,30);

        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setBounds(50,180,100,25);

        txtPhone = new JTextField();
        txtPhone.setBounds(170,180,220,30);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(50,230,100,25);

        txtEmail = new JTextField();
        txtEmail.setBounds(170,230,220,30);

        btnSave = new JButton("Save");
        btnSave.setBounds(40,320,100,35);

        btnReset = new JButton("Reset");
        btnReset.setBounds(180,320,100,35);

        btnBack = new JButton("Back");
        btnBack.setBounds(320,320,100,35);

        panel.add(title);
        panel.add(lblId);
        panel.add(txtMemberId);
        panel.add(lblName);
        panel.add(txtMemberName);
        panel.add(lblPhone);
        panel.add(txtPhone);
        panel.add(lblEmail);
        panel.add(txtEmail);

        panel.add(btnSave);
        panel.add(btnReset);
        panel.add(btnBack);

        add(panel);

        btnSave.addActionListener(e -> saveMember());

        btnReset.addActionListener(e -> {

            txtMemberId.setText("");
            txtMemberName.setText("");
            txtPhone.setText("");
            txtEmail.setText("");

        });

        btnBack.addActionListener(e -> {

            dispose();
            new Dashboard().setVisible(true);

        });

    }

    private void saveMember() {

        try {

            Member member = new Member();

            member.setMemberId(Integer.parseInt(txtMemberId.getText()));
            member.setMemberName(txtMemberName.getText());
            member.setPhone(txtPhone.getText());
            member.setEmail(txtEmail.getText());

            MemberDAO dao = new MemberDAO();

            if (dao.addMember(member)) {

                JOptionPane.showMessageDialog(this,
                        "Member Added Successfully!");

            } else {

                JOptionPane.showMessageDialog(this,
                        "Failed to Add Member!");

            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());

        }

    }

}