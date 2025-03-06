package ui.admin;

import dao.PhoneDao;
import dao.PhoneDetailDao;
import dto.PhoneDto;
import dto.PhoneDetailDto;
import ui.common.PhoneFormPanel;

import javax.swing.*;
import java.awt.*;

public abstract class PhoneBaseScreen extends JFrame {
    protected PhoneFormPanel formPanel;
    protected PhoneDao phoneDao = new PhoneDao();
    protected PhoneDetailDao phoneDetailDao = new PhoneDetailDao();

    public PhoneBaseScreen(String title) {
        setTitle(title);
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        formPanel = new PhoneFormPanel();
        add(formPanel, BorderLayout.CENTER);
    }

    protected abstract void handleAction(); // 버튼 액션을 강제 구현하도록 설정

    protected void addActionButton(String text) {
        JButton actionButton = new JButton(text);
        actionButton.addActionListener(e -> handleAction());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actionButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
