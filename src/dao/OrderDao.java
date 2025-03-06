package dao;

import common.DBManager;
import dto.OrderDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class OrderDao {

    public int addOrder(int userId, int phoneId, int salePrice) {
        int ret = -1;
        String query = "INSERT INTO orders (user_id, phone_id, sale_price, order_date) VALUES (?, ?, ?, NOW())";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, userId);
            pstmt.setInt(2, phoneId);
            pstmt.setInt(3, salePrice);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public int deleteOrder(int order_id) {
        int ret = -1;
        String query = "DELETE FROM orders WHERE order_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, order_id);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public int updateOrder(OrderDto order) {
        int ret = -1;
        String query = "UPDATE orders SET user_id = ?, phone_id = ?, sale_price = ?, order_date = ? WHERE order_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, order.getUser_id());
            pstmt.setInt(2, order.getPhone_id());
            pstmt.setInt(3, order.getSale_price());
            pstmt.setDate(4, order.getOrder_date());
            pstmt.setInt(5, order.getOrder_id());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public List<OrderDto> listOrders() {
        List<OrderDto> orders = new LinkedList<>();
        String query = "SELECT * FROM orders";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int order_id = rs.getInt("order_id");
                int user_id = rs.getInt("user_id");
                int phone_id = rs.getInt("phone_id");
                int sale_price = rs.getInt("sale_price");
                Date order_date = rs.getDate("order_date");

                OrderDto order = new OrderDto(order_id, user_id, phone_id, sale_price, order_date);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return orders;
    }
}
