package ui.admin;

import common.MybatisManager;
import dto.PhoneDto;
import dto.PhoneDetailDto;

import javax.swing.*;
import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import ui.common.PhoneDetailViewScreen;
import ui.util.BaseButton;

public class PhoneEditScreen extends BaseButton {
    private int phoneId;
    private PhoneDetailViewScreen detailViewScreen;
    private StockViewScreen stockViewScreen;

    public PhoneEditScreen(int phoneId,StockViewScreen stockViewScreen, PhoneDetailViewScreen detailViewScreen) {
        super("스마트폰 정보 수정");
        this.phoneId = phoneId;
        this.stockViewScreen = stockViewScreen;
        this.detailViewScreen = detailViewScreen;
        addButtons();
        loadPhoneData();
        setVisible(true);
    }

    @Override
    protected void handleAction() {
        SqlSession session = MybatisManager.getSession();

        try {
            PhoneDto phone = new PhoneDto(
                    phoneId,
                    formPanel.modelField.getText(),
                    formPanel.brandField.getText(),
                    Date.valueOf(formPanel.releasedAtField.getText()),
                    Integer.parseInt(formPanel.priceField.getText()),
                    Integer.parseInt(formPanel.stockField.getText())
            );

            PhoneDetailDto phoneDetail = new PhoneDetailDto(
                    phoneId,
                    formPanel.processorField.getText(),
                    formPanel.ramField.getText(),
                    formPanel.storageField.getText(),
                    Integer.parseInt(formPanel.batteryField.getText()),
                    Integer.parseInt(formPanel.weightField.getText())
            );

            if (MybatisManager.getPhoneDao(session).updatePhone(phone) > 0 && MybatisManager.getPhoneDetailDao(session).updatePhoneDetail(phoneDetail) > 0) {
                session.commit();
                JOptionPane.showMessageDialog(this, "스마트폰 정보 수정 완료!");
                dispose();

                // 디테일 화면 업데이트
                if (detailViewScreen != null) {
                    detailViewScreen.updatePhoneDetail(phone);
                }

                // 재고 조회 화면 업데이트
                if (stockViewScreen != null) {
                    stockViewScreen.updateStockView();
                }

            } else {
                JOptionPane.showMessageDialog(this, "수정 실패!", "오류", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "입력값을 확인하세요.", "오류", JOptionPane.ERROR_MESSAGE);

        } finally {
            session.close();
        }
    }

    @Override
    protected void handleDelete() {
        SqlSession session = MybatisManager.getSession();
        int confirm = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                if (MybatisManager.getPhoneDetailDao(session).deletePhoneDetail(phoneId) > 0 && MybatisManager.getPhoneDao(session).deletePhone(phoneId) > 0) {
                    session.commit();
                    JOptionPane.showMessageDialog(this, "스마트폰 정보 삭제 완료!");
                    dispose();

                    // 디테일 화면 닫기
                    if (detailViewScreen != null) {
                        detailViewScreen.dispose();
                    }

                    // 재고 조회 화면 업데이트
                    if (stockViewScreen != null) {
                        stockViewScreen.updateStockView();
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "삭제 실패!", "오류", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "삭제 중 오류 발생: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
        session.close();
    }

    private void loadPhoneData() {
        SqlSession session = MybatisManager.getSession();

        PhoneDto phone = MybatisManager.getPhoneDao(session).getPhone(phoneId);
        PhoneDetailDto phoneDetail = MybatisManager.getPhoneDetailDao(session).getPhoneDetail(phoneId);

        if (phone != null) {
            formPanel.modelField.setText(phone.getModel());
            formPanel.brandField.setText(phone.getBrand());
            formPanel.releasedAtField.setText(phone.getReleased_at().toString());
            formPanel.priceField.setText(String.valueOf(phone.getPrice()));
            formPanel.stockField.setText(String.valueOf(phone.getStock()));
        }

        if (phoneDetail != null) {
            formPanel.processorField.setText(phoneDetail.getProcessor());
            formPanel.ramField.setText(phoneDetail.getRam());
            formPanel.storageField.setText(phoneDetail.getStorage());
            formPanel.batteryField.setText(String.valueOf(phoneDetail.getBattery()));
            formPanel.weightField.setText(String.valueOf(phoneDetail.getWeight()));
        }

        session.close();
    }
}
