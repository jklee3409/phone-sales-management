package ui.user;

import dao.UserDao;
import dto.UserDto;

import javax.swing.*;
import java.awt.*;

public class RegisterScreen extends JFrame {
    private JTextField nameField, usernameField, amountField;
    private JPasswordField passwordField;
    private UserDao userDao = new UserDao();

    public RegisterScreen() {
        setTitle("회원 가입");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("이름:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(15);
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("아이디:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(15);
        add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("비밀번호:"), gbc);

        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("초기 잔액:"), gbc);

        gbc.gridx = 1;
        amountField = new JTextField(15);
        add(amountField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("가입하기");
        registerButton.addActionListener(e -> registerUser());
        add(registerButton, gbc);

        setVisible(true);
    }

    private void registerUser() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String amountText = amountField.getText().trim();

        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (userDao.isUsernameExists(username)) {
            JOptionPane.showMessageDialog(this, "이미 존재하는 아이디입니다!", "오류", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int amount = Integer.parseInt(amountText);
            UserDto user = new UserDto(0, name, username, password, amount);

            int result = userDao.addUser(user);
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "회원 가입 완료!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "회원 가입 실패!", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "잔액은 숫자로 입력하세요!", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
