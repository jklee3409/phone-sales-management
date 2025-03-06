package dao;

import common.DBManager;
import dto.OrderDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDao {

    public int addOrder(OrderDto order) {
        int ret = -1;
        String query = "INSERT INTO orders VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, order.getOrder_id());
            pstmt.setInt(2, order.getUser_id());
            pstmt.setInt(3, order.getPhone_id());
            pstmt.setInt(4, order.getSale_price());
            pstmt.setDate(5, order.getOrder_date());

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
}
