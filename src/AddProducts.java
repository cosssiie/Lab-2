import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddProducts extends JFrame {

    /**Масив для зберігання груп товарів */
    public static ArrayList<GroupOfItems> groupsList = new ArrayList<>();

    /**Масив для зберігання товарів */
    public static ArrayList<Items> productsList = new ArrayList<Items>();

    /**Вибір функції: додати групу товарів/додати товар */
    private JComboBox<String> chooseFunction;

    /**Ввести назву товару/ групи товарів */
    private JTextField nameOfGroup;

    /**Ввести опис групи товарів */
    private JTextField descriptionOfGroup;

    /**Ввести назву товару */
    private JTextField nameOfProduct;

    /**Ввести опис товару */
    private JTextField descriptionOfproduct;

    /**Ввести виробника товару */
    private JTextField producerOfProduct;

    /**Ввести кількість товарів */
    private JTextField countOfProduct;

    /**Ввести ціну товару */
    private JTextField priceOfProduct;

    /**Вибір групи товарів, куди додати товар */
    public JComboBox<String> groupNameBox;

    /**Кнопка "зберегти" */
    public JButton saveButton;

    /**Напис назви */
    private JLabel typeName;

    /**Напис опису */
    private JLabel typeDescription;

    /**Напис виробника */
    private JLabel typeProducer;

    /**Напис кількості */
    private JLabel typeCount;

    /**Напис ціни */
    private JLabel typePrice;


    private static final int width = 800;
    private static final int height = 700;
    private static final int widthOfField = 300;
    private static final int heightOfField = 70;
    private static final int widthOfButton = 170;
    private static final int heightOfButton = 70;
    
    public static final String groupsFileName = "GroupsOfProducts.txt"; //файл для збереження груп товарів
    public static final String allProducts = "AllProducts.txt"; //файл для збереження груп товарів



    public AddProducts(String name) {
        super();
        this.setTitle(name);
        this.setSize(width, height);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        init();
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
        chooseFunction.setBounds(width/2 - widthOfField/2, height/2 - 300 , widthOfField, heightOfField);
        this.getContentPane().add(chooseFunction);

        chooseFunction.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 0) {

                groupNameBox.setVisible(false);

                descriptionOfGroup.setVisible(true);

                typeDescription.setVisible(true);
                typeDescription.setBounds(width/2 - widthOfField/2, height/2  , widthOfField, heightOfField);

                nameOfGroup.setVisible(true);

                typeName.setText("Назва групи товарів:");
                typeName.setBounds(width/2 - widthOfField/2, height/2 - heightOfField*2 , widthOfField, heightOfField);

                nameOfProduct.setVisible(false);
                descriptionOfproduct.setVisible(false);
                producerOfProduct.setVisible(false);
                countOfProduct.setVisible(false);
                priceOfProduct.setVisible(false);

                typeProducer.setVisible(false);
                typeCount.setVisible(false);
                typePrice.setVisible(false);


            } else {
                groupNameBox.setVisible(true);

                nameOfProduct.setVisible(true);
                descriptionOfproduct.setVisible(true);
                producerOfProduct.setVisible(true);
                countOfProduct.setVisible(true);
                priceOfProduct.setVisible(true);

                nameOfGroup.setVisible(false);
                descriptionOfGroup.setVisible(false);

                typeName.setText("Назва товару:");
                typeName.setBounds(width/2 - 270, height/2 - heightOfField*2 , widthOfField, heightOfField);

                typeDescription.setVisible(true);
                typeDescription.setText("Опис товару:");
                typeDescription.setBounds(width/2 + widthOfField/2, height/2 - heightOfField*2 , widthOfField, heightOfField);

                typeProducer.setVisible(true);
                typeCount.setVisible(true);
                typePrice.setVisible(true);

            }
        });

        typeName = new JLabel("Назва групи товарів: ");
        typeName.setBounds(width/2 - widthOfField/2, height/2 - heightOfField*2 , widthOfField, heightOfField);
        typeName.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeName);

        typeDescription = new JLabel("Опис групи товарів: ");
        typeDescription.setBounds(width/2 - widthOfField/2, height/2  , widthOfField, heightOfField);
        typeDescription.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeDescription);

        typeProducer = new JLabel("Виробник: ");
        typeProducer.setBounds(width/2 - 270, height/2 - 40 , widthOfField, heightOfField);
        typeProducer.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeProducer);
        typeProducer.setVisible(false);

        typeCount = new JLabel("Кількість на складі: ");
        typeCount.setBounds(width/2 + widthOfField/2, height/2 - 40 , widthOfField, heightOfField);
        typeCount.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeCount);
        typeCount.setVisible(false);

        typePrice = new JLabel("Ціна за одиницю: ");
        typePrice.setBounds(width/2 - 55, height/2  + 60, widthOfField, heightOfField);
        typePrice.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typePrice);
        typePrice.setVisible(false);


        nameOfGroup = new JTextField();
        nameOfGroup.setHorizontalAlignment(JTextField.CENTER);
        nameOfGroup.setBounds(width/2 - widthOfField/2, height/2 - heightOfField , widthOfField, heightOfField);
        nameOfGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfGroup);

        descriptionOfGroup = new JTextField();
        descriptionOfGroup.setHorizontalAlignment(JTextField.CENTER);
        descriptionOfGroup.setBounds(width/2 - widthOfField/2, height/2 + heightOfField , widthOfField, heightOfField);
        descriptionOfGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(descriptionOfGroup);

        nameOfProduct = new JTextField();
        nameOfProduct.setHorizontalAlignment(JTextField.CENTER);
        nameOfProduct.setBounds(width/2 - 350, height/2 - 90 , widthOfField, heightOfField);
        nameOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfProduct);
        nameOfProduct.setVisible(false);

        descriptionOfproduct = new JTextField();
        descriptionOfproduct.setHorizontalAlignment(JTextField.CENTER);
        descriptionOfproduct.setBounds(width/2 + 50, height/2 - 90, widthOfField, heightOfField);
        descriptionOfproduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(descriptionOfproduct);
        descriptionOfproduct.setVisible(false);

        producerOfProduct = new JTextField();
        producerOfProduct.setHorizontalAlignment(JTextField.CENTER);
        producerOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        producerOfProduct.setBounds(width/2 - 350, 365 , widthOfField, heightOfField);
        this.getContentPane().add(producerOfProduct);
        producerOfProduct.setVisible(false);

        countOfProduct = new JTextField();
        countOfProduct.setHorizontalAlignment(JTextField.CENTER);
        countOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        countOfProduct.setBounds(width/2 + 50, 365, widthOfField, heightOfField);
        this.getContentPane().add(countOfProduct);
        countOfProduct.setVisible(false);

        priceOfProduct = new JTextField();
        priceOfProduct.setHorizontalAlignment(JTextField.CENTER);
        priceOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        priceOfProduct.setBounds(width/2 - widthOfField/2, 460, widthOfField, heightOfField);
        this.getContentPane().add(priceOfProduct);
        priceOfProduct.setVisible(false);

        groupNameBox = new JComboBox<>();
        groupNameBox.setBounds(width/2 - widthOfField/2, 130 , widthOfField, heightOfField);
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
            saveButtonActionPerformed();
        });
    }

    private void saveButtonActionPerformed() {
        if (chooseFunction.getSelectedIndex() == 0) {
            String groupName = nameOfGroup.getText();
            String groupDescription = descriptionOfGroup.getText();

            if (!groupName.isEmpty() && !groupDescription.isEmpty()) {
                addGroup(groupName, groupDescription);
                nameOfGroup.setText("");
                descriptionOfGroup.setText("");
            }
        }else{
            String groupName = groupNameBox.getSelectedItem().toString();
            String name = nameOfProduct.getText();
            String description = descriptionOfproduct.getText();
            String producer = producerOfProduct.getText();
            int count = parseInt(countOfProduct.getText());
            int price = parseInt(priceOfProduct.getText());

            if (!name.isEmpty() && !description.isEmpty() && !producer.isEmpty() && count > 0 && price > 0) {
                addProductToGroup(groupName, name, description, producer, count, price);
                nameOfProduct.setText("");
                descriptionOfproduct.setText("");
                producerOfProduct.setText("");
                countOfProduct.setText("");
                priceOfProduct.setText("");
            }
        }
    }

    private void addGroup(String groupName, String description) {
        if (isGroupUnique(groupName)) {
            GroupOfItems group = new GroupOfItems(groupName, description);
            groupsList.add(group);

            try {
                FileWriter writer = new FileWriter(groupsFileName, true);
                writer.write(groupName + "\n");
                writer.close();
                JOptionPane.showMessageDialog(this, "Група товарів \"" + groupName + "\" успішно додана!");

                groupNameBox.addItem(groupName);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка при додаванні групи товарів.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Група товарів з такою назвою вже існує!");
        }
    }

    private void addProductToGroup(String groupName, String name, String description, String producer, int count, int pricePerOne) {
        if (isProductUnique(groupName, name)) {
            Items item = new Items(name, description, producer, count, pricePerOne);
            productsList.add(item);
            try {
                // Зберігання товару в файлі групи
                File groupFile = new File(groupName + ".txt");
                FileWriter writer = new FileWriter(groupFile, true);
                writer.write(name + "," + description + "," + producer + "," + count + "," + pricePerOne + "\n");
                writer.close();

                // Збереження товару в файлі "AllProducts.txt"
                FileWriter allProductsWriter = new FileWriter(allProducts, true);
                allProductsWriter.write(groupName + "," + name + "," + description + "," + producer + "," + count + "," + pricePerOne + "\n");
                allProductsWriter.close();

                JOptionPane.showMessageDialog(this, "Товар \"" + name + "\" успішно додано в групу \"" + groupName + "\"!");
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Помилка при додаванні товару.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Товар з такою назвою вже існує в обраній групі!");
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
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні груп товарів.");
        }
    }

    private boolean isGroupUnique(String groupName) {
        try {
            File groupFile = new File(groupsFileName);
            if (groupFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(groupFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parameters = line.split(",");
                    if (parameters.length > 0 && parameters[0].equals(groupName)) {
                        reader.close();
                        return false;
                    }
                }
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    private boolean isProductUnique(String groupName, String productName) {
        try {
            File groupFile = new File(groupName + ".txt");
            if (groupFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(groupFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parameters = line.split(",");
                    if (parameters.length > 0 && parameters[0].equals(productName)) {
                        reader.close();
                        return false;
                    }
                }
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }



    public static void main(String[] args) {
        AddProducts products = new AddProducts("Додавання товарів");
        products.setVisible(true);

    }
}
