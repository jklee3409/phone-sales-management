package dao;

import common.DBManager;
import dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

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
}
