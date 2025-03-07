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

    protected void addButtons() {
        JPanel buttonPanel = new JPanel(); // 하나의 버튼 패널 사용

        JButton actionButton = new JButton("수정하기");
        actionButton.addActionListener(e -> handleAction());
        buttonPanel.add(actionButton); // 수정 버튼 추가

        JButton deleteButton = new JButton("삭제하기");
        deleteButton.addActionListener(e -> handleDelete());
        buttonPanel.add(deleteButton); // 삭제 버튼 추가

        add(buttonPanel, BorderLayout.SOUTH); // 패널 추가
    }
}
