import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AddProducts extends JFrame {

    /**Масив для зберігання груп товарів */
    private ArrayList<GroupOfItems> groupsList = new ArrayList<>();

    /**Масив для зберігання товарів */
    private ArrayList<Items> productsList = new ArrayList<Items>();

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
    private JComboBox<String> groupNameBox;

    /**Кнопка "зберегти" */
    private JButton saveButton;

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
        chooseFunction.setBounds(width/2 - widthOfField/2, height/2 - heightOfField*4 , widthOfField, heightOfField);
        this.getContentPane().add(chooseFunction);

        chooseFunction.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 0) {
                groupNameBox.setVisible(false);
                descriptionOfGroup.setVisible(true);
                descriptionOfGroup.setBounds(width/2 - widthOfField/2, height/2 + heightOfField , widthOfField, heightOfField);
                typeDescription.setVisible(true);
                typeDescription.setBounds(width/2 - widthOfField/2, height/2  , widthOfField, heightOfField);
                nameOfGroup.setBounds(width/2 - widthOfField/2, height/2 - heightOfField , widthOfField, heightOfField);
                typeName.setText("Назва групи товарів:");
                typeName.setBounds(width/2 - widthOfField/2, height/2 - heightOfField*2 , widthOfField, heightOfField);


            } else {
                groupNameBox.setVisible(true);
                descriptionOfGroup.setVisible(false);
                typeDescription.setVisible(false);
                nameOfGroup.setBounds(width/2 - widthOfField/2, height/2 + heightOfField , widthOfField, heightOfField);
                typeName.setText("Назва товару:");
                typeName.setBounds(width/2 - widthOfField/2, height/2 + heightOfField/4 , widthOfField, heightOfField);


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
        nameOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfProduct);

        descriptionOfproduct = new JTextField();
        descriptionOfproduct.setHorizontalAlignment(JTextField.CENTER);
        descriptionOfproduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(descriptionOfproduct);

        producerOfProduct = new JTextField();
        producerOfProduct.setHorizontalAlignment(JTextField.CENTER);
        producerOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(producerOfProduct);

        countOfProduct = new JTextField();
        countOfProduct.setHorizontalAlignment(JTextField.CENTER);
        countOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(countOfProduct);

        priceOfProduct = new JTextField();
        priceOfProduct.setHorizontalAlignment(JTextField.CENTER);
        priceOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(priceOfProduct);


        groupNameBox = new JComboBox<>();
        groupNameBox.setBounds(width/2 - widthOfField/2, 145 , widthOfField, heightOfField);
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
            String description = descriptionOfGroup.getText();

            if (!groupName.isEmpty() && !description.isEmpty()) {
                addGroup(groupName, description);
                nameOfGroup.setText("");
                descriptionOfGroup.setText("");
            }
        }
    }

    private void addGroup(String groupName, String description) {
        GroupOfItems group = new GroupOfItems(groupName, description);
        groupsList.add(group);

        try {
            FileWriter writer = new FileWriter(groupsFileName, true);
            writer.write(groupName + "\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Группа товаров \"" + groupName + "\" успешно добавлена!");

            groupNameBox.addItem(groupName);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при добавлении группы товаров.");
        }
    }

    private void addProductToGroup(String groupName, String name, String description, String producer, int count, int pricePerOne) {
        Items item = new Items(name, description, producer, count, pricePerOne);
        productsList.add(item);
        try {
            File groupFile = new File(groupName + ".txt");
            FileWriter writer = new FileWriter(groupFile, true);
            writer.write(name + "," + description + "," + producer + "," + count + "," + pricePerOne + "\n");
            writer.close();
            JOptionPane.showMessageDialog(this, "Товар \"" + name + "\" успешно добавлен в группу \"" + groupName + "\"!");
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка при добавлении товара в группу.");
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
            JOptionPane.showMessageDialog(this, "Ошибка при загрузке списка групп товаров.");
        }
    }

    public static void main(String[] args) {
        AddProducts products = new AddProducts("Добавление товаров");
        products.setVisible(true);
    }
}
