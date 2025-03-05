package dao;

import common.DBManager;
import dto.PhoneDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class PhoneDao {

    // Add phone to database
    public int addPhone(PhoneDto phone) {
        int ret = -1;
        String query = "INSERT INTO phone VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, phone.getPhone_id());
            pstmt.setString(2, phone.getModel());
            pstmt.setString(3, phone.getBrand());
            pstmt.setDate(4, phone.getReleased_at());
            pstmt.setInt(5, phone.getPrice());
            pstmt.setInt(6, phone.getStock());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }
}
