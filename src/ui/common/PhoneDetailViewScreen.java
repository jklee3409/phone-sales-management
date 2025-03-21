package ui.common;

import common.MybatisManager;
import dto.PhoneDetailDto;
import dto.PhoneDto;

import dto.UserDto;
import javax.swing.*;
import java.awt.*;
import org.apache.ibatis.session.SqlSession;
import ui.admin.PhoneEditScreen;
import ui.admin.StockViewScreen;
import ui.user.PurchaseScreen;

public class PhoneDetailViewScreen extends JFrame {
    private PurchaseScreen purchaseScreen;

    public PhoneDetailViewScreen(int phoneId, UserDto user, StockViewScreen stockViewScreen, PurchaseScreen purchaseScreen, boolean isPurchaseMode, boolean isAdmin) {
        try (SqlSession session = MybatisManager.getSession()){

            setTitle("스마트폰 상세 정보");
            setSize(420, 450);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);

            PhoneDto phone = MybatisManager.getPhoneDao(session).getPhone(phoneId);
            PhoneDetailDto phoneDetail = MybatisManager.getPhoneDetailDao(session).getPhoneDetail(phoneId);
            this.purchaseScreen = purchaseScreen;

            JPanel container = createContainer(phone, phoneDetail);

            if (isPurchaseMode) {
                JButton purchaseButton = new JButton("구매하기");
                purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                purchaseButton.addActionListener(e -> purchasePhone(phone, user));
                container.add(Box.createRigidArea(new Dimension(0, 10)));
                container.add(purchaseButton);
            }

            if (isAdmin) {
                JButton editButton = new JButton("수정 및 삭제");
                editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                editButton.addActionListener(e -> new PhoneEditScreen(phone.getPhone_id(), stockViewScreen,this));
                container.add(Box.createRigidArea(new Dimension(0, 10)));
                container.add(editButton);
            }

            JButton closeButton = new JButton("닫기");
            closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeButton.addActionListener(e -> dispose());
            container.add(Box.createRigidArea(new Dimension(0, 10)));
            container.add(closeButton);

            add(container);
            setVisible(true);
        }
    }

    public void updatePhoneDetail(PhoneDto phone) {
        try (SqlSession session = MybatisManager.getSession()){

            getContentPane().removeAll(); // 기존 UI 제거 후 재구성
            repaint();
            revalidate();

            PhoneDetailDto phoneDetail = MybatisManager.getPhoneDetailDao(session).getPhoneDetail(phone.getPhone_id());
            JPanel container = createContainer(phone, phoneDetail);

            JButton closeButton = new JButton("닫기");
            closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            closeButton.addActionListener(e -> dispose());
            container.add(Box.createRigidArea(new Dimension(0, 10)));
            container.add(closeButton);

            getContentPane().add(container);
            repaint();
            revalidate();
        }
    }

    private JPanel createContainer(PhoneDto phone, PhoneDetailDto phoneDetail) {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel(phone.getModel() + " (" + phone.getBrand() + ")");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(titleLabel);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(new JSeparator(SwingConstants.HORIZONTAL));
        container.add(Box.createRigidArea(new Dimension(0, 10)));

        container.add(createInfoPanel("출시일", phone.getReleased_at().toString()));
        container.add(createInfoPanel("가격", phone.getPrice() + "원"));
        container.add(createInfoPanel("재고", phone.getStock() + "개"));
        container.add(createInfoPanel("프로세서", phoneDetail.getProcessor()));
        container.add(createInfoPanel("RAM", phoneDetail.getRam()));
        container.add(createInfoPanel("저장 공간", phoneDetail.getStorage()));
        container.add(createInfoPanel("배터리", phoneDetail.getBattery() + " mAh"));
        container.add(createInfoPanel("무게", phoneDetail.getWeight() + " g"));

        return container;
    }

    // 정보 패널을 생성
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


    private void purchasePhone(PhoneDto phone, UserDto user) {

        try (SqlSession session = MybatisManager.getSession()){

            if (user.getAmount() < phone.getPrice()) {
                JOptionPane.showMessageDialog(this, "잔액이 부족합니다!", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "이 스마트폰을 구매하시겠습니까?", "구매 확인", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            // 주문 생성
            int orderResult = MybatisManager.getOrderDao(session).addOrder(user.getUser_id(), phone.getPhone_id(), phone.getPrice());
            if (orderResult > 0) {

                // 잔액 차감
                user.setAmount(user.getAmount() - phone.getPrice());
                MybatisManager.getUserDao(session).updateAmount(user.getUser_id(), user.getAmount());

                // 재고 감소
                MybatisManager.getPhoneDao(session).updateStock(phone.getPhone_id(), phone.getStock() - 1);

                session.commit(); //구매 성공 시 변경 사항 commit

                // ui 업데이트
                if(purchaseScreen != null) {
                    purchaseScreen.updateUI();
                }

            } else {
                JOptionPane.showMessageDialog(this, "구매 실패!", "오류", JOptionPane.ERROR_MESSAGE);
            }

            JOptionPane.showMessageDialog(this, "구매 완료!");
            dispose();
        }
    }
}


