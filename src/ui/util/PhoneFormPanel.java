package ui.util;

import javax.swing.*;
import java.awt.*;

public class PhoneFormPanel extends JPanel {
    public JTextField modelField, brandField, releasedAtField, priceField, stockField;
    public JTextField processorField, ramField, storageField, batteryField, weightField;

    public PhoneFormPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        modelField = addField("모델명:", gbc, 0);
        brandField = addField("브랜드:", gbc, 1);
        releasedAtField = addField("출시일 (YYYY-MM-DD):", gbc, 2);
        priceField = addField("가격:", gbc, 3);
        stockField = addField("재고:", gbc, 4);
        processorField = addField("프로세서:", gbc, 5);
        ramField = addField("RAM:", gbc, 6);
        storageField = addField("저장 공간:", gbc, 7);
        batteryField = addField("배터리(mAh):", gbc, 8);
        weightField = addField("무게(g):", gbc, 9);
    }

    private JTextField addField(String label, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(label), gbc);
        gbc.gridx = 1;
        JTextField textField = new JTextField(15);
        add(textField, gbc);
        return textField;
    }
}
