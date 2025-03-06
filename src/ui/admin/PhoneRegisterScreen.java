package ui.admin;

import dao.PhoneDao;
import dao.PhoneDetailDao;
import dto.PhoneDto;
import dto.PhoneDetailDto;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class PhoneRegisterScreen extends JFrame {
    private JTextField modelField, brandField, releasedAtField, priceField, stockField;
    private JTextField processorField, ramField, storageField, batteryField, weightField;
    private PhoneDao phoneDao = new PhoneDao();
    private PhoneDetailDao phoneDetailDao = new PhoneDetailDao();

    public PhoneRegisterScreen() {
        setTitle("스마트폰 등록");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 간격 설정
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
        add(new JLabel("배터리(mAh):"), gbc);
        gbc.gridx = 1;
        batteryField = new JTextField(15);
        add(batteryField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("무게(g):"), gbc);
        gbc.gridx = 1;
        weightField = new JTextField(15);
        add(weightField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton registerButton = new JButton("등록");
        registerButton.addActionListener(e -> registerPhone());
        add(registerButton, gbc);

        setVisible(true);
    }

    private void registerPhone() {
        try {
            // 1. 스마트폰 정보 생성
            PhoneDto phone = new PhoneDto(
                    0, // phone_id는 자동 생성됨
                    modelField.getText(),
                    brandField.getText(),
                    Date.valueOf(releasedAtField.getText()),
                    Integer.parseInt(priceField.getText()),
                    Integer.parseInt(stockField.getText())
            );

            // 2️. phone 테이블에 추가
            int result = phoneDao.addPhone(phone);
            if (result <= 0) {
                throw new Exception("스마트폰 등록 실패");
            }

            // 3️. 최신 phone_id 가져오기
            int phoneId = phoneDao.getLatestPhoneId();

            // 4️. 스마트폰 상세 정보 생성
            PhoneDetailDto phoneDetail = new PhoneDetailDto(
                    phoneId, // 최신 phone_id 활용
                    processorField.getText(),
                    ramField.getText(),
                    storageField.getText(),
                    Integer.parseInt(batteryField.getText()),
                    Integer.parseInt(weightField.getText())
            );

            // 5️. phone_detail 테이블에 추가
            int detailResult = phoneDetailDao.addPhoneDetail(phoneDetail);
            if (detailResult > 0) {
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


