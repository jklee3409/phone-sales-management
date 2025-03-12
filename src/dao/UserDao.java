package dao;

import dto.UserDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

// delete 구현 x
public interface UserDao {

    @Select("SELECT COUNT(*) FROM users WHERE username = #{username} AND password = #{password}")
    boolean authenticateUser(String username, String password);

    @Insert("INSERT INTO users (name, username, password, amount) "
            + "VALUES (#{name}, #{username}, #{password}, #{amount})")
    int addUser(UserDto user);

    @Select("SELECT * FROM users WHERE username = #{username}")
    UserDto findUser(String username);

    @Update("UPDATE users SET amount = #{amount} WHERE user_id = #{user_id}")
    int updateAmount(int user_id, int amount);

    @Select("SELECT COUNT(*) FROM users WHERE username = #{username}")
    boolean isUsernameExists(String username);
}
