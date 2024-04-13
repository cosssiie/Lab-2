import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProductSearch extends JFrame {

    /** Шлях до теки з файлами груп товарів */
    private static final String GROUPS_DIRECTORY = "GroupsOfProducts.txt";

    /** Ввести назву товару */
    private JTextField nameOfProduct;

    /** Кнопка пошуку */
    private JButton searchButton;

    /** Вивести інформацію про товар */
    private JTextArea information;

    /** Напис "Назва товару" */
    private JLabel nameOfItem;

    private static final int width = 800;
    private static final int height = 700;
    private static final int widthOfField = 300;
    private static final int heightOfField = 70;
    private static final int widthOfButton = 170;
    private static final int heightOfButton = 70;

    public ProductSearch(String name) {
        super();
        this.setTitle(name);
        this.setSize(width, height);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        init();
    }

    private void init() {

        nameOfProduct = new JTextField();
        nameOfProduct.setHorizontalAlignment(JTextField.CENTER);
        Font font = nameOfProduct.getFont();
        nameOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        nameOfProduct.setBounds(width / 2 - widthOfField / 2, 100, widthOfField, heightOfField);
        this.getContentPane().add(nameOfProduct);

        nameOfItem = new JLabel("Назва товару:");
        nameOfItem.setBounds(width / 2 - 50, 40, widthOfField, heightOfField);
        nameOfItem.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfItem);

        searchButton = new JButton("Знайти товар");
        searchButton.setBounds(width / 2 - widthOfButton / 2, 200, widthOfButton, heightOfButton);
        searchButton.setBackground(new Color(102, 153, 102, 250));
        searchButton.setForeground(Color.WHITE);
        Font buttonFont = searchButton.getFont();
        searchButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(searchButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchProduct();
            }
        });

        information = new JTextArea(10, 30);
        information.setEditable(false);
        information.setFont(new Font("Arial", Font.PLAIN, 19));
        JScrollPane scrollPane = new JScrollPane(information);
        scrollPane.setBounds(width / 2 - 200, 300, 400, 300);
        this.getContentPane().add(scrollPane);
    }

    private void searchProduct() {
        String productName = nameOfProduct.getText();
        if (!productName.isEmpty()) {
            String groupFileName = "AllProducts.txt";
            File groupFile = new File(groupFileName);
            if (groupFile.exists()) {
                String productInfo = readProductInfoFromFile(groupFile, productName);
                information.setText(productInfo);
            } else {
                information.setText("Товар не знайдено.");
            }
        } else {
            information.setText("Введіть назву товару для пошуку.");
        }
    }

    private String readProductInfoFromFile(File file, String productName) {
        StringBuilder productInfo = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productData = line.split(",");
                if (productData.length >= 5 && productData[0].equalsIgnoreCase(productName)) {
                    productInfo.append("Назва товару: ").append(productData[0]).append("\n");
                    productInfo.append("Опис товару: ").append(productData[1]).append("\n");
                    productInfo.append("Виробник товару: ").append(productData[2]).append("\n");
                    productInfo.append("Кількість на складі: ").append(productData[3]).append("\n");
                    productInfo.append("Ціна за одиницю: ").append(productData[4]).append("\n");
                    return productInfo.toString();
                }
            }
            // Якщо товар не знайдено
            productInfo.append("Товар з назвою \"").append(productName).append("\" не знайдено.");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return productInfo.toString();
    }


    public static void main(String[] args) {
        // Приклад створення і відображення вікна пошуку товару
        ProductSearch search = new ProductSearch("Пошук товару");
        search.setVisible(true);
    }
}
