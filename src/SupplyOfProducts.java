import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
public class SupplyOfProducts extends JFrame {


    /**Вибір функції: списати товар/поставка товарів */
    private JComboBox<String> chooseFunction;

    /**Вибір товару */
    private JComboBox<String> chooseProduct;

    /**Скільки поставили/списали */
    private JTextField supplyCount;

    /**Напис "Скільки списати"/"Скільки поставили" */
    private JLabel supplyCountLabel;

    private static final int width = 800;
    private static final int height = 700;
    private static final int widthOfField = 300;
    private static final int heightOfField = 70;
    private static final int widthOfButton = 170;
    private static final int heightOfButton = 70;


    public SupplyOfProducts(String name) {
        super();
        this.setTitle(name);
        this.setSize(width, height);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        init();
    }

    private void init() {
    }


    public static void main(String[] args) {
        SupplyOfProducts supply = new SupplyOfProducts("Поставка та списання товарів");
        supply.setVisible(true);
    }
}
