import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SupplyOfProducts extends JFrame {

    /** Вибір функції: списати товар/поставка товарів */
    private JComboBox<String> chooseFunction;

    /** Вибір товару */
    private JComboBox<String> chooseProduct;

    /** Вибір групи товарів */
    private JComboBox<String> chooseGroup;

    /** Скільки поставили/списали */
    private JTextField supplyCount;

    /** Напис "Скільки списати"/"Скільки поставили" */
    private JLabel supplyCountLabel;

    public static final String groupsFileName = "GroupsOfProducts.txt";
    public static final String allProducts = "AllProducts.txt"; //файл для збереження груп товарів

    private static final int width = 800;
    private static final int height = 700;
    private static final int widthOfField = 300;
    private static final int heightOfField = 70;
    private static final int widthOfButton = 170;
    private static final int heightOfButton = 70;

    public SupplyOfProducts(String name, ArrayList<GroupOfItems> groupsList) {
        super();
        this.setTitle(name);
        this.setSize(width, height);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        this.setLocationRelativeTo(null);
        init(groupsList);
        loadGroupNamesFromFile();
    }

    private void init(ArrayList<GroupOfItems> groupsList) {
        chooseFunction = new JComboBox<>();
        chooseFunction.addItem("Списати товар");
        chooseFunction.addItem("Поставка товару");
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        chooseFunction.setRenderer(renderer);
        Font font = chooseFunction.getFont();
        chooseFunction.setFont(font.deriveFont(Font.BOLD, 16f));
        chooseFunction.setBounds(width / 2 - widthOfField / 2, height / 2 - 300, widthOfField, heightOfField);
        this.getContentPane().add(chooseFunction);

        chooseGroup = new JComboBox<>(); // Додаємо вибірник груп товарів
        chooseGroup.setBounds(width / 2 - widthOfField / 2, height / 2 - 200, widthOfField, heightOfField);
        chooseGroup.setRenderer(renderer);
        chooseGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(chooseGroup);

        chooseProduct = new JComboBox<>();
        chooseProduct.setBounds(width / 2 - widthOfField / 2, height / 2 - 50, widthOfField, heightOfField);
        chooseProduct.setRenderer(renderer);
        chooseProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(chooseProduct);

        supplyCountLabel = new JLabel("Скільки списати:");
        supplyCountLabel.setBounds(width / 2 - 70, height - 320, widthOfField, heightOfField);
        supplyCountLabel.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(supplyCountLabel);

        supplyCount = new JTextField();
        supplyCount.setHorizontalAlignment(JTextField.CENTER);
        supplyCount.setFont(font.deriveFont(Font.BOLD, 16f));
        supplyCount.setBounds(width / 2 - widthOfField / 2, 450, widthOfField, heightOfField);
        this.getContentPane().add(supplyCount);

        JButton saveButton = new JButton("Зберегти");
        saveButton.setBounds(width / 2 - widthOfButton / 2, height - 150, widthOfButton, heightOfButton);
        saveButton.setBackground(new Color(102, 153, 102, 250));
        saveButton.setForeground(Color.WHITE);
        Font buttonFont = saveButton.getFont();
        saveButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(saveButton);

        saveButton.addActionListener(e -> {
            saveButtonActionPerformed();
        });


        chooseFunction.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 0) {
                supplyCountLabel.setText("Скільки списати:");

            } else {
                supplyCountLabel.setText("Скільки додати:");
            }
        });

        chooseGroup.addActionListener(e -> {
            String selectedGroup = chooseGroup.getSelectedItem() + ".txt";
            loadItemsForSelectedGroup(selectedGroup);
        });

        chooseProduct.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 0) {
                showQuantityOfProduct();
            }
        });
    }

    private void showQuantityOfProduct() {
        //TODO вивести у supplyCountLabel (додати до початку рядка) кількість товару, яка наявна
        //System.out.println("showQuantityOfProduct");
    }

    private void loadItemsForSelectedGroup(String selectedGroup) {
        chooseProduct.removeAllItems();

        try {
            File file = new File(selectedGroup);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parameters = line.split(",");
                if (parameters.length > 0) {
                    chooseProduct.addItem(parameters[0]);
                }
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні товарів для обраної групи.", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void loadGroupNamesFromFile() {
        try {
            File file = new File(groupsFileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                chooseGroup.addItem(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні імен груп товарів.", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void subtractItems(String selectedGroup, String productName, int quantityToSubtract) {
        try {
            File file = new File(selectedGroup);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            int newCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] parameters = line.split(",");
                if (parameters.length > 0 && parameters[0].equals(productName)) {
                    int currentCount = Integer.parseInt(parameters[3]);
                    newCount = currentCount - quantityToSubtract;
                    if (newCount < 0) {
                        JOptionPane.showMessageDialog(this, "Недостатньо товару для списання.", "Помилка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {
                        JOptionPane.showMessageDialog(this, "Товар успішно списано.\n Актуальна кількість товару " + newCount);
                        showQuantityOfProduct();
                    }
                    parameters[3] = String.valueOf(newCount);
                    line = String.join(",", parameters);
                }
                sb.append(line).append("\n");
            }
            reader.close();

            Files.write(Paths.get(selectedGroup), sb.toString().getBytes());

            // Оновлення файлу AllProducts.txt
            updateAllProducts(productName, newCount);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при списанні товарів.");
        }
    }




    private void addItems(String selectedGroup, String productName, int quantityToAdd) {
        try {
            File file = new File(selectedGroup);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            int newCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] parameters = line.split(",");
                if (parameters.length > 0 && parameters[0].equals(productName)) {
                    int currentCount = Integer.parseInt(parameters[3]);
                    newCount = currentCount + quantityToAdd;
                    parameters[3] = String.valueOf(newCount);
                    line = String.join(",", parameters);
                }
                sb.append(line).append("\n");
            }
            reader.close();

            Files.write(Paths.get(selectedGroup), sb.toString().getBytes());
            JOptionPane.showMessageDialog(this, "Товар успішно додано.\n Актуальна кількість товару " + newCount);
            showQuantityOfProduct();

            // Оновлення файлу AllProducts.txt
            updateAllProducts(productName, newCount);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при додаванні товарів.", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateAllProducts(String productName, int newQuantity) {
        try {
            File file = new File(allProducts);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parameters = line.split(",");
                if (parameters.length > 0 && parameters[0].equals(productName)) {
                    parameters[3] = String.valueOf(newQuantity);
                    line = String.join(",", parameters);
                }
                sb.append(line).append("\n");
            }
            reader.close();

            Files.write(Paths.get("AllProducts.txt"), sb.toString().getBytes());

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при оновленні файлу AllProducts.txt.", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }



    private void saveButtonActionPerformed() {
        String selectedGroup = chooseGroup.getSelectedItem() + ".txt";
        String selectedProduct = (String) chooseProduct.getSelectedItem();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(supplyCount.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Введіть кількість товару правильно.", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (chooseFunction.getSelectedIndex() == 0) {
            subtractItems(selectedGroup, selectedProduct, quantity);
            supplyCount.setText("");
        } else {
            addItems(selectedGroup, selectedProduct, quantity);
            supplyCount.setText("");
        }
    }


    public static void main(String[] args) {
        SupplyOfProducts supply = new SupplyOfProducts("Поставка та списання товарів", AddProducts.groupsList);
        supply.setVisible(true);
    }
}