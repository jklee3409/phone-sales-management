package dao;

import common.DBManager;
import dto.PhoneDetailDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    public int deletePhoneDetail(int phone_id) {
        int ret = -1;
        String query = "DELETE FROM phone_detail WHERE phone_id = ?";

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

    public int updatePhoneDetail(PhoneDetailDto phoneDetail) {
        int ret = -1;
        String query = "UPDATE phone_detail SET processor = ?, ram = ?, storage = ?, battery = ?, weight = ? WHERE phone_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, phoneDetail.getProcessor());
            pstmt.setString(2, phoneDetail.getRam());
            pstmt.setString(3, phoneDetail.getStorage());
            pstmt.setInt(4, phoneDetail.getBattery());
            pstmt.setInt(5, phoneDetail.getWeight());
            pstmt.setInt(6, phoneDetail.getPhone_id());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public List<PhoneDetailDto> listPhoneDetail() {
        String query = "SELECT * FROM phone_detail";
        List<PhoneDetailDto> list = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PhoneDetailDto phoneDetail = new PhoneDetailDto(
                        rs.getInt("phone_id"),
                        rs.getString("processor"),
                        rs.getString("ram"),
                        rs.getString("storage"),
                        rs.getInt("battery"),
                        rs.getInt("weight")
                );

                list.add(phoneDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }

        return list;
    }

    public PhoneDetailDto getPhoneDetail(int phone_id) {
        PhoneDetailDto phoneDetail = null;
        String query = "SELECT * FROM phone_detail WHERE phone_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, phone_id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                phoneDetail = new PhoneDetailDto(
                        rs.getInt("phone_id"),
                        rs.getString("processor"),
                        rs.getString("ram"),
                        rs.getString("storage"),
                        rs.getInt("battery"),
                        rs.getInt("weight")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }

        return phoneDetail;
    }
}
