package ui.admin;

import javax.swing.*;
import java.awt.*;

public class AdminScreen extends JFrame {
    public AdminScreen() {
        setTitle("관리자 화면");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(3, 1, 10, 10));

        JButton stockButton = new JButton("재고 조회");
        JButton registerButton = new JButton("스마트폰 등록");
        JButton orderButton = new JButton("주문 조회");

        stockButton.addActionListener(e -> new StockViewScreen());
        registerButton.addActionListener(e -> new PhoneRegisterScreen());
        orderButton.addActionListener(e -> new OrderViewScreen());

        add(stockButton);
        add(registerButton);
        add(orderButton);

        setVisible(true);
    }
}
