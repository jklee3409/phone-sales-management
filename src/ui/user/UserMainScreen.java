package ui.user;

import dao.UserDao;
import dto.UserDto;

import javax.swing.*;
import java.awt.*;

public class UserMainScreen extends JFrame {
    private UserDto user;
    private JLabel balanceLabel;

    public UserMainScreen(String username) {
        this.user = UserDao.findUser(username);

        setTitle("메인 메뉴");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🔹 사용자 잔액 표시
        balanceLabel = new JLabel("잔액: " + user.getAmount() + "원", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        add(balanceLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton purchaseButton = new JButton("구매하기");
        purchaseButton.addActionListener(e -> new PurchaseScreen(user.getUsername()));

        JButton updateBalanceButton = new JButton("잔액 충전");
        updateBalanceButton.addActionListener(e -> new BalanceUpdateScreen(user, this));

        buttonPanel.add(purchaseButton);
        buttonPanel.add(updateBalanceButton);

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    // UI 잔액 업데이트
    public void updateBalanceUI(int newAmount) {
        user.setAmount(newAmount);
        balanceLabel.setText("잔액: " + newAmount + "원");
    }
}

