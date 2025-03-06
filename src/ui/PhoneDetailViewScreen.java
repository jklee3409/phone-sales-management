package ui;

import dao.PhoneDetailDao;
import dao.PhoneDao;
import dto.PhoneDetailDto;
import dto.PhoneDto;

import javax.swing.*;
import java.awt.*;

public class PhoneDetailViewScreen extends JFrame {
    private PhoneDetailDao phoneDetailDao = new PhoneDetailDao();
    private PhoneDao phoneDao = new PhoneDao();

    public PhoneDetailViewScreen(int phoneId) {
        setTitle("스마트폰 상세 정보");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 전체 컨테이너 설정
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 스마트폰 기본 정보 가져오기
        PhoneDto phone = phoneDao.getPhone(phoneId);
        PhoneDetailDto phoneDetail = phoneDetailDao.getPhoneDetail(phoneId);

        if (phone == null || phoneDetail == null) {
            JOptionPane.showMessageDialog(this, "해당 스마트폰의 정보를 찾을 수 없습니다!", "오류", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }

        // 타이틀 라벨 (스마트폰 모델명)
        JLabel titleLabel = new JLabel(phone.getModel() + " (" + phone.getBrand() + ")");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(titleLabel);

        // 구분선
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(new JSeparator(SwingConstants.HORIZONTAL));
        container.add(Box.createRigidArea(new Dimension(0, 10)));

        // 상세 정보 패널
        container.add(createInfoPanel("출시일", phone.getReleased_at().toString()));
        container.add(createInfoPanel("가격", phone.getPrice() + "원"));
        container.add(createInfoPanel("재고", phone.getStock() + "개"));
        container.add(createInfoPanel("프로세서", phoneDetail.getProcessor()));
        container.add(createInfoPanel("RAM", phoneDetail.getRam()));
        container.add(createInfoPanel("저장 공간", phoneDetail.getStorage()));
        container.add(createInfoPanel("배터리", phoneDetail.getBattery() + " mAh"));
        container.add(createInfoPanel("무게", phoneDetail.getWeight() + " g"));

        // 닫기 버튼
        JButton closeButton = new JButton("닫기");
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addActionListener(e -> dispose());
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(closeButton);

        add(container);
        setVisible(true);
    }

    // 정보 패널 생성 (라벨 + 값)
    private JPanel createInfoPanel(String label, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel labelComponent = new JLabel(label + ": ");
        labelComponent.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        JLabel valueComponent = new JLabel(value);
        valueComponent.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        panel.add(labelComponent, BorderLayout.WEST);
        panel.add(valueComponent, BorderLayout.EAST);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        return panel;
    }
}


