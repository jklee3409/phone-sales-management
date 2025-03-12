package dao;

import dto.PhoneDetailDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface PhoneDetailDao {

    @Insert("INSERT INTO phone_detail VALUES (#{phone_id}, #{processor}, #{ram}, #{storage}, #{battery}, #{weight})")
    int addPhoneDetail(PhoneDetailDto phoneDetail);

    @Delete("DELETE FROM phone_detail WHERE phone_id = #{phone_id}")
    int deletePhoneDetail(int phone_id);

    @Update("UPDATE phone_detail "
            + "SET processor = #{processor}, ram = #{ram}, storage = #{storage}, battery = #{battery}, weight = #{weight} "
            + "WHERE phone_id = #{phone_id}")
    int updatePhoneDetail(PhoneDetailDto phoneDetail);

    @Select("SELECT * FROM phone_detail WHERE phone_id = #{phone_id}")
    PhoneDetailDto getPhoneDetail(int phone_id);
}
