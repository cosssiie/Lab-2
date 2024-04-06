import javax.swing.*;
import java.awt.*;
import java.io.*;

public class AddProducts extends JFrame {

    /**Вибір функції: додати групу товарів/додати товар */
    private JComboBox<String> chooseFunction;

    /**Ввести назву товару/ групи товарів */
    private JTextField nameOfProduct;

    /**Вибір групи товарів, куди додати товар */
    private JComboBox<String> groupNameBox;

    /**Кнопка "зберегти" */
    private JButton saveButton;


    private static final int width = 600;
    private static final int height = 600;
    private static final int widthOfField = 350;
    private static final int heightOfField = 70;
    private static final int widthOfButton = 170;
    private static final int heightOfButton = 70;
    
    private static final String groupsFileName = "GroupsOfProducts.txt"; //файл для збереження груп товарів

    public AddProducts(String name) {
        super();
        this.setTitle(name);
        this.setSize(width, height);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        init();
        this.add(chooseFunction); //додати товар
        this.add(nameOfProduct);  //назва товару/групи товарів
        this.add(groupNameBox);  //додані групи товарів
        this.add(saveButton);   //кнопка "зберігти"
    }

    private void init() {
        chooseFunction = new JComboBox<>();
        chooseFunction.addItem("Додати групу товарів");
        chooseFunction.addItem("Додати товар");
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        chooseFunction.setRenderer(renderer);
        Font font = chooseFunction.getFont();
        chooseFunction.setFont(font.deriveFont(Font.BOLD, 16f));
        chooseFunction.setBounds(width/2 - widthOfField/2, height/2 - heightOfField*3 , widthOfField, heightOfField);
        this.getContentPane().add(chooseFunction);

        chooseFunction.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 1) {
                groupNameBox.setVisible(true);
            } else {
                groupNameBox.setVisible(false);
            }
        });

        nameOfProduct = new JTextField();
        nameOfProduct.setBounds(width/2 - widthOfField/2, height/2 + heightOfField/2 , widthOfField, heightOfField);
        nameOfProduct.setHorizontalAlignment(JTextField.CENTER);
        nameOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfProduct);

        groupNameBox = new JComboBox<>();
        groupNameBox.setBounds(width/2 - widthOfField/2, height/2 - heightOfField , widthOfField, heightOfField);
        groupNameBox.setRenderer(renderer);
        groupNameBox.setFont(font.deriveFont(Font.BOLD, 16f));
        groupNameBox.setVisible(false);
        this.getContentPane().add(groupNameBox);


        loadGroupNamesFromFile();

        saveButton = new JButton("Зберегти");
        saveButton.setBounds(width/2 - widthOfButton/2, height - 150, widthOfButton, heightOfButton);
        saveButton.setBackground(new Color(102, 153, 102, 250));
        saveButton.setForeground(Color.WHITE);
        Font buttonFont = saveButton.getFont();
        saveButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(saveButton);

        saveButton.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 0) {
                // Добавляем новую группу товаров
                String groupName = nameOfProduct.getText();
                if (!groupName.isEmpty()) {
                    addGroup(groupName);
                    nameOfProduct.setText("");
                    groupNameBox.addItem(groupName);
                }
            } else if (chooseFunction.getSelectedIndex() == 1) {
                // Добавляем товар в выбранную группу
                String groupName = (String) groupNameBox.getSelectedItem();
                String productName = nameOfProduct.getText();
                if (!groupName.isEmpty() && !productName.isEmpty()) {
                    addProductToGroup(groupName, productName);
                    nameOfProduct.setText("");
                }
            }
        });
    }
    
    private void addGroup(String groupName) {
        try {
            FileWriter writer = new FileWriter(groupsFileName, true);
            writer.write(groupName + "\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Група товарів \"" + groupName + "\" успішно додана!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при додаванні групи товарів.");
        }
    }


    private void addProductToGroup(String groupName, String productName) {
        try {
            File groupFile = new File(groupName + ".txt");
            FileWriter writer = new FileWriter(groupFile, true);
            writer.write(productName + "\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Товар \"" + productName + "\" успішно доданий в групу \"" + groupName + "\"!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при додаванні товару в групу.");
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
                groupNameBox.addItem(line);
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні списку груп товарів.");
        }
    }

    public static void main(String[] args) {
        AddProducts products = new AddProducts("Add products");
        products.setVisible(true);
    }
}
