package dao;

import common.DBManager;
import dto.PhoneDetailDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PhoneDetailDao {

    public int addPhoneDetail(PhoneDetailDto phoneDetail) {
        int ret = -1;
        String query = "INSERT INTO phone_detail VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, phoneDetail.getPhone_id());
            pstmt.setString(2, phoneDetail.getProcessor());
            pstmt.setString(3, phoneDetail.getRam());
            pstmt.setString(4, phoneDetail.getStorage());
            pstmt.setInt(5, phoneDetail.getBattery());
            pstmt.setInt(6, phoneDetail.getWeight());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }
}
