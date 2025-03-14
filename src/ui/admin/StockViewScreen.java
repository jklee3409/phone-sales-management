package ui.admin;

import common.MybatisManager;
import dto.PhoneDto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.SqlSession;
import ui.common.PhoneDetailViewScreen;
import ui.util.UtilMethod;

public class StockViewScreen extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField searchField; // 검색 입력 필드
    private JButton searchButton; // 검색 버튼

    public StockViewScreen() {
        setTitle("재고 조회");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 상단 검색 패널
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(20);
        searchButton = new JButton("검색");

        searchPanel.add(new JLabel("모델명 검색:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {"ID", "모델명", "브랜드", "출시일", "가격", "재고"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 셀 편집 불가
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        loadStockData();

        // 더블 클릭 시 상세 조회 화면 이동
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // 더블 클릭 감지
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        int phoneId = (int) table.getValueAt(selectedRow, 0);
                        new PhoneDetailViewScreen(phoneId, null, StockViewScreen.this, null,false, true);
                    }
                }
            }
        });

        // 검색 버튼 이벤트
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterStockData(searchField.getText());
            }
        });

        setVisible(true);
    }

    public void updateStockView() {
        loadStockData();
    }

    private void loadStockData() {

        try (SqlSession session = MybatisManager.getSession()){
            tableModel.setRowCount(0);
            List<PhoneDto> phones = MybatisManager.getPhoneDao(session).listPhone();

            loadData(phones);
        }

    }

    private void filterStockData(String model) {

        try (SqlSession session = MybatisManager.getSession()) {
            tableModel.setRowCount(0);

            List<PhoneDto> filteredPhones = MybatisManager.getPhoneDao(session).searchPhoneList(model);

            loadData(filteredPhones);
        }
    }

    private void loadData(List<PhoneDto> filteredPhones) {
        for (PhoneDto phone : filteredPhones) {
            Vector<Object> row = UtilMethod.loadRow(phone);

            tableModel.addRow(row);
        }
    }
}


