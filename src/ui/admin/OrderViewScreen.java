package ui.admin;

import dao.OrderDao;
import dto.OrderDto;
import common.MybatisManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;
import org.apache.ibatis.session.SqlSession;

public class OrderViewScreen extends JFrame {

    public OrderViewScreen() {
        setTitle("주문 조회");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"주문 ID", "사용자 ID", "폰 ID", "판매 가격", "주문 날짜"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 셀 수정 방지
            }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        loadOrderData(tableModel);

        setVisible(true);
    }

    private void loadOrderData(DefaultTableModel tableModel) {

        try(SqlSession session = MybatisManager.getSession()) {

            OrderDao orderDao = MybatisManager.getOrderDao(session);
            List<OrderDto> orders = orderDao.listOrders();

            for (OrderDto order : orders) {
                Vector<Object> row = new Vector<>();
                row.add(order.getOrder_id());
                row.add(order.getUser_id());
                row.add(order.getPhone_id());
                row.add(order.getSale_price());
                row.add(order.getOrder_date());
                tableModel.addRow(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "주문 데이터를 불러오는 데 실패했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
