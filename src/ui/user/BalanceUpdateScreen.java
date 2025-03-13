package ui.user;

import common.MybatisManager;
import dto.UserDto;

import javax.swing.*;
import java.awt.*;
import org.apache.ibatis.session.SqlSession;

public class BalanceUpdateScreen extends JFrame {
    private UserDto user;
    private JTextField amountField;
    private UserMainScreen mainScreen; // 메인 화면 업데이트

    public BalanceUpdateScreen(UserDto user, UserMainScreen mainScreen) {
        this.user = user;
        this.mainScreen = mainScreen;

        setTitle("잔액 충전");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 5, 5));

        add(new JLabel("충전할 금액:", SwingConstants.CENTER));

        amountField = new JTextField();
        add(amountField);

        JButton chargeButton = new JButton("충전하기");
        chargeButton.addActionListener(e -> updateBalance());
        add(chargeButton);

        setVisible(true);
    }

    // 잔액 업데이트
    private void updateBalance() {
        SqlSession session = MybatisManager.getSession();

        try {
            int amountToAdd = Integer.parseInt(amountField.getText().trim());
            if (amountToAdd <= 0) {
                JOptionPane.showMessageDialog(this, "충전 금액은 0보다 커야 합니다!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int newBalance = user.getAmount() + amountToAdd;
            MybatisManager.getUserDao(session).updateAmount(user.getUser_id(), newBalance);
            user.setAmount(newBalance);

            if (mainScreen != null) {
                mainScreen.updateBalanceUI(newBalance);
            }

            JOptionPane.showMessageDialog(this, "잔액 충전 완료! 현재 잔액: " + newBalance + "원");
            dispose();
            session.commit();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "숫자만 입력하세요!", "오류", JOptionPane.ERROR_MESSAGE);

        } finally {
            session.close();
        }
    }
}
