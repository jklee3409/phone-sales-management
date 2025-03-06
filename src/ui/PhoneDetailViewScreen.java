package ui;

import dao.PhoneDetailDao;
import dto.PhoneDetailDto;

import javax.swing.*;
import java.awt.*;

public class PhoneDetailViewScreen extends JFrame {
    private PhoneDetailDao phoneDetailDao = new PhoneDetailDao();

    public PhoneDetailViewScreen(int phoneId) {
        setTitle("스마트폰 상세 정보");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        PhoneDetailDto phoneDetail = phoneDetailDao.getPhoneDetail(phoneId);

        if (phoneDetail == null) {
            JOptionPane.showMessageDialog(this, "해당 스마트폰의 상세 정보를 찾을 수 없습니다!", "오류", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        add(new JLabel("프로세서:"));
        add(new JLabel(phoneDetail.getProcessor()));

        add(new JLabel("RAM:"));
        add(new JLabel(phoneDetail.getRam()));

        add(new JLabel("저장 공간:"));
        add(new JLabel(phoneDetail.getStorage()));

        add(new JLabel("배터리 용량:"));
        add(new JLabel(phoneDetail.getBattery() + " mAh"));

        add(new JLabel("무게:"));
        add(new JLabel(phoneDetail.getWeight() + " g"));

        setVisible(true);
    }
}

