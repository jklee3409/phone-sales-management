package dao;

import dto.OrderDto;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

public interface OrderDao {

    @Insert("INSERT INTO orders (user_id, phone_id, sale_price, order_date) VALUES (#{userID}, #{phone_id}, #{salePrice}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "order_id")
    int addOrder(int userId, int phone_id, int salePrice);

    @Select("SELECT * FROM orders")
    List<OrderDto> listOrders();
}
