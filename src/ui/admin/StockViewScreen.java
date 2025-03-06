package ui.admin;

import dao.PhoneDao;
import dto.PhoneDto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;
import ui.common.PhoneDetailViewScreen;

public class StockViewScreen extends JFrame {
    private PhoneDao phoneDao = new PhoneDao();
    private DefaultTableModel tableModel;
    private JTable table;

    public StockViewScreen() {
        setTitle("재고 조회");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "모델명", "브랜드", "출시일", "가격", "재고"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 셀 편집 불가
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadStockData();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블 클릭 감지
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int phoneId = (int) table.getValueAt(selectedRow, 0); // 첫 번째 열(phone_id) 가져오기
                        new PhoneDetailViewScreen(phoneId, null, StockViewScreen.this,false, true);
                    }
                }
            }
        });

        setVisible(true);
    }

    public void updateStockView() {
        loadStockData();
    }

    private void loadStockData() {
        tableModel.setRowCount(0);
        List<PhoneDto> phones = phoneDao.listPhone();

        for (PhoneDto phone : phones) {
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

