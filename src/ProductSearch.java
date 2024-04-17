import javax.swing.*;
import java.awt.*;

public class ProductSearch extends JDialog {
    private Main main;

    /** Ввести назву товару */
    private JTextField nameOfProduct;

    /** Кнопка пошуку */
    private JButton searchButton;

    /** Вивести інформацію про товар */
    private JTextArea information;

    private static final int WIDTH_OF_FRAME = 500;
    private static final int HEIGHT_OF_FRAME = 700;
    private static final int WIDTH_OF_FIELD = 300;
    private static final int HEIGHT_OF_FIELD = 70;
    private static final int WIDTH_OF_BUTTON = 170;
    private static final int HEIGHT_OF_BUTTON = 70;

    public ProductSearch(String name, Main main, JFrame parent) {
        super(parent, name, true);
        this.main = main;
        this.setTitle(name);
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        this.setLocationRelativeTo(null);
    }
    public void start(){
        init();
        this.setVisible(true);
    }

    private void init() {
        if (nameOfProduct != null){
            return;
        }
        nameOfProduct = new JTextField();
        nameOfProduct.setHorizontalAlignment(JTextField.CENTER);
        Font font = nameOfProduct.getFont();
        nameOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        nameOfProduct.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, 100, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        add(nameOfProduct);

        JLabel label1 = new JLabel("Пошук товару");
        label1.setBounds(WIDTH_OF_FRAME / 2 - 50, 10, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        label1.setFont(font.deriveFont(Font.BOLD, 16f));
        add(label1);
        JLabel label2 = new JLabel("* - будь-яка к-ть будь-яких символів");
        label2.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, 30, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        label2.setFont(font.deriveFont(Font.BOLD, 16f));
        add(label2);
        JLabel label3 = new JLabel("? - один будь-який символ");
        label3.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, 50, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        label3.setFont(font.deriveFont(Font.BOLD, 16f));
        add(label3);

        searchButton = new JButton("Знайти товар");
        searchButton.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_BUTTON / 2, 200, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        searchButton.setBackground(new Color(102, 153, 102, 250));
        searchButton.setForeground(Color.WHITE);
        Font buttonFont = searchButton.getFont();
        searchButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
        add(searchButton);

        searchButton.addActionListener(e -> searchProduct());

        information = new JTextArea(10, 30);
        information.setEditable(false);
        information.setFont(new Font("Arial", Font.PLAIN, 19));
        JScrollPane scrollPane = new JScrollPane(information);
        scrollPane.setBounds(WIDTH_OF_FRAME / 2 - 200, 300, 400, 300);
        add(scrollPane);
    }

    private void searchProduct() {
        information.setText("");
        String searchRegex = nameOfProduct.getText();
        if (searchRegex.isEmpty()){
            JOptionPane.showMessageDialog(this, "Введіть вираз для пошуку.", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        searchRegex = searchRegex.replaceAll("[*]+", ".*");
        searchRegex = searchRegex.replaceAll("[?]", ".");
        boolean isFound = false;
        for (GroupOfItems group : main.getGroupsList()){
            for (Items product : group.productsList){
                if (product.getName().matches(searchRegex)){
                    information.append(product.getName() + "\n");
                    information.append("    Група товару: " + group.getNameOfGroup() + "\n");
                    information.append("    Ціна товару: " + product.getPricePerOne() + "\n");
                    information.append("    Кількість на складі: " + product.getCount() + "\n");
                    information.append("    Виробник: " + product.getProducer() + "\n");
                    information.append("    Опис: " + product.getDescription() + "\n");
                    information.append("--------------------------------------------------------------------------------\n");
                    isFound = true;
                }
            }
        }
        if (!isFound){
            JOptionPane.showMessageDialog(this, "Товар не знайдено.");
        }
    }
}
