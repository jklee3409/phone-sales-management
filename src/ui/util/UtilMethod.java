package ui.util;

import dto.PhoneDto;
import java.util.Vector;

public class UtilMethod {

    public static Vector<Object> loadRow(PhoneDto phone) {
        Vector<Object> row = new Vector<>();

        row.add(phone.getPhone_id());
        row.add(phone.getModel());
        row.add(phone.getBrand());
        row.add(phone.getReleased_at());
        row.add(phone.getPrice());
        row.add(phone.getStock());

        return row;
    }
}
