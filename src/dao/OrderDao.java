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
}
