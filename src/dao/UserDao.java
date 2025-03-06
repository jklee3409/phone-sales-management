package dao;

import common.DBManager;
import dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public static boolean authenticateUser(String username, String password) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            return rs.next(); // 데이터가 있으면 로그인 성공

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }
    }

    public int addUser(UserDto user) {
        int ret = -1;
        String query = "INSERT INTO user VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, user.getUser_id());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getUsername());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getAmount());

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public int deleteUser(int user_id) {
        int ret = -1;
        String query = "DELETE FROM user WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, user_id);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public int updateAmount(int user_id, int amount) {
        int ret = -1;
        String query = "UPDATE user SET amount = ? WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, amount);
            pstmt.setInt(2, user_id);

            ret = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            DBManager.releaseConnection(pstmt, conn);
        }

        return ret;
    }

    public boolean isUsernameExists(String username) {
        String query = "SELECT * FROM users WHERE username = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getConnection();
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);

            rs = pstmt.executeQuery();

            return rs.next(); // 데이터가 있으면 이미 존재하는 사용자

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        } finally {
            DBManager.releaseConnection(rs, pstmt, conn);
        }
    }
}
