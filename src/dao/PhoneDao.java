package dao;

import dto.PhoneDto;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PhoneDao {

    @Insert("INSERT INTO phone VALUES (#{phone_id}, #{model}, #{brand}, #{released_at}, #{price}, #{stock})")
    int addPhone(PhoneDto phone);

    @Delete("DELETE FROM phone WHERE phone_id = #{phone_id}")
    int deletePhone(int phone_id);

    @Update("UPDATE phone "
            + "SET model = #{model}, brand = #{brand}, released_at = #{released_at}, price = #{price}, stock = #{stock} "
            + "WHERE phone_id = #{phone_id}")
    int updatePhone(PhoneDto phone);

    @Select("SELECT * FROM phone")
    List<PhoneDto> listPhone();

    @Select("SELECT * FROM phone WHERE phone_id = #{phone_id}")
    PhoneDto getPhone(int phone_id);

    @Update("UPDATE phone SET stock = #{stock} WHERE phone_id = #{phone_id}")
    int updateStock(int phone_id, int stock);

    @Select("SELECT MAX(phone_id) FROM phone")
    int getLatestPhoneId();

    @Select("SELECT * FROM phone WHERE model LIKE CONCAT('%', #{model}, '%')")
    List<PhoneDto> searchPhoneList(String model);
}
