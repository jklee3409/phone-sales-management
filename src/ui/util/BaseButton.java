package ui.util;

import dao.PhoneDao;
import dao.PhoneDetailDao;

import javax.swing.*;
import java.awt.*;

public abstract class BaseButton extends JFrame {
    protected PhoneFormPanel formPanel;
    protected PhoneDao phoneDao = new PhoneDao();
    protected PhoneDetailDao phoneDetailDao = new PhoneDetailDao();

    public BaseButton(String title) {
        setTitle(title);
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        formPanel = new PhoneFormPanel();
        add(formPanel, BorderLayout.CENTER);
    }

    protected abstract void handleAction(); // 버튼 액션을 강제 구현하도록 설정
    protected abstract void handleDelete(); // 삭제 버튼 액션을 강제 구현하도록 설정

    protected void addActionButton(String text) {
        JButton actionButton = new JButton(text);
        actionButton.addActionListener(e -> handleAction());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actionButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    protected void addDeleteButton(String text) {
        JButton deleteButton = new JButton(text);
        deleteButton.addActionListener(e -> handleDelete());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
