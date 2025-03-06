package dao;

import common.DBManager;
import dto.PhoneDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public int deletePhone(int phone_id) {
        int ret = -1;
        String query = "DELETE FROM phone WHERE phone_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, phone_id);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public int updatePhone(PhoneDto phone) {
        int ret = -1;
        String query = "UPDATE phone SET model = ?, brand = ?, released_At = ?, price = ?, stock = ? WHERE phone_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, phone.getModel());
            pstmt.setString(2, phone.getBrand());
            pstmt.setDate(3, phone.getReleased_at());
            pstmt.setInt(4, phone.getPrice());
            pstmt.setInt(5, phone.getStock());
            pstmt.setInt(6, phone.getPhone_id());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public List<PhoneDto> listPhone() {
        List<PhoneDto> list = new ArrayList<>();
        String query = "SELECT * FROM phone";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PhoneDto phone = new PhoneDto(
                    rs.getInt("phone_id"),
                    rs.getString("model"),
                    rs.getString("brand"),
                    rs.getDate("released_at"),
                    rs.getInt("price"),
                    rs.getInt("stock")
                );
                list.add(phone);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }

        return list;
    }

    public PhoneDto getPhone(int phone_id) {
        String query = "SELECT * FROM phone WHERE phone_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        PhoneDto phone = null;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, phone_id);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                phone = new PhoneDto(
                        rs.getInt("phone_id"),
                        rs.getString("model"),
                        rs.getString("brand"),
                        rs.getDate("released_at"),
                        rs.getInt("price"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }

        return phone;
    }

    public int getLatestPhoneId() {
        String query = "SELECT MAX(phone_id) FROM phone";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int lastIndex = -1;
        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                lastIndex = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }

        return lastIndex;
    }
}
