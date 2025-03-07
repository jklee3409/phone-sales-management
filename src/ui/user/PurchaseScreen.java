package ui.user;

import dao.PhoneDao;
import dao.UserDao;
import dto.PhoneDto;
import dto.UserDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;
import ui.common.PhoneDetailViewScreen;

public class PurchaseScreen extends JFrame {
    private UserDto user;
    private PhoneDao phoneDao = new PhoneDao();
    private UserDao userDao = new UserDao();
    private JLabel balanceLabel;
    private JTable phoneTable;
    private DefaultTableModel tableModel;
    private UserMainScreen userMainScreen;

    public PurchaseScreen(String username, UserMainScreen userMainScreen) {
        this.user = userDao.findUser(username);
        this.userMainScreen = userMainScreen;

        setTitle("스마트폰 구매");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        balanceLabel = new JLabel("잔액: " + user.getAmount() + "원");
        panel.add(balanceLabel);
        add(panel, "North");

        String[] columnNames = {"ID", "모델명", "브랜드", "출시일", "가격", "재고"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 셀 수정 방지
            }
        };

        phoneTable = new JTable(tableModel);
        phoneTable.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane(phoneTable);
        add(scrollPane);

        loadPhoneData();

        // 더블 클릭 시 상세 정보 창 띄우기
        phoneTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = phoneTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int phoneId = (int) tableModel.getValueAt(selectedRow, 0);
                        new PhoneDetailViewScreen(phoneId, user, null,PurchaseScreen.this, true, false); // 구매 가능 모드, 관리자 아님
                    }
                }
            }
        });

        setVisible(true);
    }

    public void updateUI() {
        int newAmount = user.getAmount();

        userMainScreen.updateBalanceUI(newAmount); // 메인 화면 잔액 업데이트
        balanceLabel.setText("잔액: " + newAmount + "원");
        tableModel.setRowCount(0); // 기존 데이터 삭제
        loadPhoneData(); // 재고가 있는 제품만 다시 로드
    }

    private void loadPhoneData() {
        List<PhoneDto> phones = phoneDao.listPhone();
        for (PhoneDto phone : phones) {
            if (phone.getStock() > 0) { // 재고가 있는 제품만 표시
                Vector<Object> row = new Vector<>();

                row.add(phone.getPhone_id());
                row.add(phone.getModel());
                row.add(phone.getBrand());
                row.add(phone.getReleased_at());
                row.add(phone.getPrice());
                row.add(phone.getStock());

                tableModel.addRow(row);
            }
        }
    }
}

