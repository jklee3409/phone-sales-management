package ui.admin;

import dao.PhoneDao;
import dao.PhoneDetailDao;
import dto.PhoneDto;
import dto.PhoneDetailDto;

import javax.swing.*;
import java.awt.*;

public class PhoneEditScreen extends JFrame {
    private JTextField modelField, brandField, releasedAtField, priceField, stockField;
    private JTextField processorField, ramField, storageField, batteryField, weightField;
    private PhoneDao phoneDao = new PhoneDao();
    private PhoneDetailDao phoneDetailDao = new PhoneDetailDao();
    private int phoneId;
    private StockViewScreen stockViewScreen; // UI 업데이트를 위해 추가

    public PhoneEditScreen(int phoneId) {
        this.phoneId = phoneId;
        this.stockViewScreen = stockViewScreen;

        setTitle("스마트폰 정보 수정");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("모델명:"), gbc);
        gbc.gridx = 1;
        modelField = new JTextField(15);
        add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("브랜드:"), gbc);
        gbc.gridx = 1;
        brandField = new JTextField(15);
        add(brandField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("출시일 (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        releasedAtField = new JTextField(15);
        add(releasedAtField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("가격:"), gbc);
        gbc.gridx = 1;
        priceField = new JTextField(15);
        add(priceField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("재고:"), gbc);
        gbc.gridx = 1;
        stockField = new JTextField(15);
        add(stockField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("프로세서:"), gbc);
        gbc.gridx = 1;
        processorField = new JTextField(15);
        add(processorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("RAM:"), gbc);
        gbc.gridx = 1;
        ramField = new JTextField(15);
        add(ramField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("저장 공간:"), gbc);
        gbc.gridx = 1;
        storageField = new JTextField(15);
        add(storageField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("배터리:"), gbc);
        gbc.gridx = 1;
        batteryField = new JTextField(15);
        add(batteryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("무게:"), gbc);
        gbc.gridx = 1;
        weightField = new JTextField(15);
        add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = new JButton("수정하기");
        saveButton.addActionListener(e -> updatePhoneInfo());
        add(saveButton, gbc);

        loadPhoneData(); // 기존 정보 불러오기
        setVisible(true);
    }

    // 🔹 기존 데이터 불러오기
    private void loadPhoneData() {
        PhoneDto phone = phoneDao.getPhone(phoneId);
        PhoneDetailDto phoneDetail = phoneDetailDao.getPhoneDetail(phoneId);

        if (phone != null) {
            modelField.setText(phone.getModel());
            brandField.setText(phone.getBrand());
            releasedAtField.setText(phone.getReleased_at().toString());
            priceField.setText(String.valueOf(phone.getPrice()));
            stockField.setText(String.valueOf(phone.getStock()));
        }

        if (phoneDetail != null) {
            processorField.setText(phoneDetail.getProcessor());
            ramField.setText(phoneDetail.getRam());
            storageField.setText(phoneDetail.getStorage());
            batteryField.setText(String.valueOf(phoneDetail.getBattery()));
            weightField.setText(String.valueOf(phoneDetail.getWeight()));
        }
    }

    // 🔹 스마트폰 정보 수정
    private void updatePhoneInfo() {
        try {
            PhoneDto phone = new PhoneDto(
                    phoneId,
                    modelField.getText(),
                    brandField.getText(),
                    java.sql.Date.valueOf(releasedAtField.getText()),
                    Integer.parseInt(priceField.getText()),
                    Integer.parseInt(stockField.getText())
            );

            PhoneDetailDto phoneDetail = new PhoneDetailDto(
                    phoneId,
                    processorField.getText(),
                    ramField.getText(),
                    storageField.getText(),
                    Integer.parseInt(batteryField.getText()),
                    Integer.parseInt(weightField.getText())
            );

            int phoneResult = phoneDao.updatePhone(phone);
            int detailResult = phoneDetailDao.updatePhoneDetail(phoneDetail);

            if (phoneResult > 0 && detailResult > 0) {
                JOptionPane.showMessageDialog(this, "스마트폰 정보 수정 완료!");

                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "수정 실패!", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "입력값을 확인하세요.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}

