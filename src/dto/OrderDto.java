package dto;

import java.util.Date;

public class OrderDto {
    private int order_id;
    private int user_id;
    private int phone_id;
    private int sale_price;
    private Date order_date;

    public OrderDto(int order_id, int user_id, int phone_id, int sale_price, Date order_date) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.phone_id = phone_id;
        this.sale_price = sale_price;
        this.order_date = order_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(int phone_id) {
        this.phone_id = phone_id;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
}
