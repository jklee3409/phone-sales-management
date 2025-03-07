package ui.admin;

import dto.PhoneDto;
import dto.PhoneDetailDto;

import javax.swing.*;
import java.sql.Date;
import ui.util.PhoneBaseScreen;

public class PhoneRegisterScreen extends PhoneBaseScreen {
    public PhoneRegisterScreen() {
        super("스마트폰 등록");
        addActionButton("등록");
        setVisible(true);
    }

    @Override
    protected void handleAction() {
        try {
            PhoneDto phone = new PhoneDto(
                    0,
                    formPanel.modelField.getText(),
                    formPanel.brandField.getText(),
                    Date.valueOf(formPanel.releasedAtField.getText()),
                    Integer.parseInt(formPanel.priceField.getText()),
                    Integer.parseInt(formPanel.stockField.getText())
            );

            int result = phoneDao.addPhone(phone);
            if (result <= 0) throw new Exception("스마트폰 등록 실패");

            int phoneId = phoneDao.getLatestPhoneId();
            PhoneDetailDto phoneDetail = new PhoneDetailDto(
                    phoneId,
                    formPanel.processorField.getText(),
                    formPanel.ramField.getText(),
                    formPanel.storageField.getText(),
                    Integer.parseInt(formPanel.batteryField.getText()),
                    Integer.parseInt(formPanel.weightField.getText())
            );

            if (phoneDetailDao.addPhoneDetail(phoneDetail) > 0) {
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
