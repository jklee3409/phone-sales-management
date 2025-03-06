package ui.common;

import dao.UserDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.user.PurchaseScreen;
import ui.user.RegisterScreen;
import ui.admin.AdminScreen;
import ui.user.UserMainScreen;

public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginScreen() {
        setTitle("로그인");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙 정렬
        setLayout(new BorderLayout());

        // 패널 생성
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // 컴포넌트 추가
        panel.add(new JLabel("아이디:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("비밀번호:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("로그인");
        registerButton = new JButton("회원가입");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // 버튼 이벤트 추가
        loginButton.addActionListener(new LoginAction());
        registerButton.addActionListener(e -> new RegisterScreen());

        setVisible(true);
    }

    // 로그인 검증 로직 (관리자 / 일반 사용자)
    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(LoginScreen.this, "아이디와 비밀번호를 입력하세요!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 1. 관리자 계정 체크
            if (username.equals("root") && password.equals("1234")) {
                JOptionPane.showMessageDialog(LoginScreen.this, "관리자 로그인 성공!");
                dispose();  // 현재 창 닫기
                new AdminScreen();  // 관리자 화면으로 이동
                return;
            }

            // 2. 일반 사용자 인증 (DB 확인)
            if (UserDao.authenticateUser(username, password)) {
                JOptionPane.showMessageDialog(LoginScreen.this, "로그인 성공!");
                dispose();  // 현재 창 닫기
                new UserMainScreen(username);  // 유저 화면으로 이동
            } else {
                JOptionPane.showMessageDialog(LoginScreen.this, "로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
            }
        }
    }
}
