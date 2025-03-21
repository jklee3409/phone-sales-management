package ui.admin;

import common.MybatisManager;
import dto.PhoneDto;
import dto.PhoneDetailDto;

import java.awt.*;
import javax.swing.*;
import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import ui.util.PhoneFormPanel;

public class PhoneRegisterScreen extends JFrame{
    private PhoneFormPanel formPanel;

    public PhoneRegisterScreen() {
        super("스마트폰 등록");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        formPanel = new PhoneFormPanel();
        add(formPanel, BorderLayout.CENTER);
        addActionButton();
        setVisible(true);
    }

    private void addActionButton() {
        JButton actionButton = new JButton("등록");
        actionButton.addActionListener(e -> handleAction());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actionButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleAction() {
       try (SqlSession session = MybatisManager.getSession()){

           try {
               PhoneDto phone = new PhoneDto(
                       0,
                       formPanel.modelField.getText(),
                       formPanel.brandField.getText(),
                       Date.valueOf(formPanel.releasedAtField.getText()),
                       Integer.parseInt(formPanel.priceField.getText()),
                       Integer.parseInt(formPanel.stockField.getText())
               );

               int result = MybatisManager.getPhoneDao(session).addPhone(phone);
               if (result <= 0) throw new Exception("스마트폰 등록 실패");

               int phoneId = MybatisManager.getPhoneDao(session).getLatestPhoneId();
               PhoneDetailDto phoneDetail = new PhoneDetailDto(
                       phoneId,
                       formPanel.processorField.getText(),
                       formPanel.ramField.getText(),
                       formPanel.storageField.getText(),
                       Integer.parseInt(formPanel.batteryField.getText()),
                       Integer.parseInt(formPanel.weightField.getText())
               );

               if (MybatisManager.getPhoneDetailDao(session).addPhoneDetail(phoneDetail) > 0) {
                   session.commit();
                   JOptionPane.showMessageDialog(this, "스마트폰 등록 완료!");
                   dispose();
               } else {
                   throw new Exception("스마트폰 상세 정보 등록 실패");
               }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(this, "등록 실패! " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
           }
       }
    }
}
