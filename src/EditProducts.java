import javax.swing.*;
import java.awt.*;
import java.io.*;

import static java.lang.Integer.parseInt;

public class EditProducts extends JDialog {
    private final Main main;

    /**Вибір функції:  група товарів/товар */
    private JComboBox<String> chooseSubject;

    /**Вибір функції */
    private JComboBox<String> chooseFunction;

    /**Ввести назву товару/ групи товарів */
    private JTextField nameOfGroup;

    /**Ввести опис групи товарів */
    private JTextField descriptionOfGroup;

    /**Ввести назву товару */
    private JTextField nameOfProduct;

    /**Ввести опис товару */
    private JTextField descriptionOfProduct;

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


    private static final int WIDTH_OF_FRAME = 800;
    private static final int HEIGHT_OF_FRAME = 700;
    private static final int WIDTH_OF_FIELD = 300;
    private static final int HEIGHT_OF_FIELD = 70;
    private static final int WIDTH_OF_BUTTON = 170;
    private static final int HEIGHT_OF_BUTTON = 70;

    public EditProducts(String name, Main main, JFrame parent) {
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
        updateInformation();
        this.setVisible(true);
    }

    private void init() {

        chooseSubject = new JComboBox<>();
        chooseSubject.addItem("групу товарів");
        chooseSubject.addItem("товар");
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        chooseSubject.setRenderer(renderer);
        Font font = chooseSubject.getFont();
        chooseSubject.setFont(font.deriveFont(Font.BOLD, 16f));
        chooseSubject.setBounds(WIDTH_OF_FRAME /2 + 10, HEIGHT_OF_FRAME /2 - 300 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        this.getContentPane().add(chooseSubject);

        chooseSubject.addActionListener(e -> {
            functionActionPerformed();
        });

        chooseFunction = new JComboBox<>();
        chooseFunction.addItem("Додати");
        chooseFunction.addItem("Редагувати");
        chooseFunction.addItem("Видалити");
        chooseFunction.setRenderer(renderer);
        chooseFunction.setFont(font.deriveFont(Font.BOLD, 16f));
        chooseFunction.setBounds(WIDTH_OF_FRAME /2 - 10 - WIDTH_OF_FIELD, HEIGHT_OF_FRAME /2 - 300 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        add(chooseFunction);

        chooseFunction.addActionListener(e -> {
            functionActionPerformed();
        });

        typeName = new JLabel("Назва групи товарів: ");
        typeName.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD *2 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        typeName.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeName);

        typeDescription = new JLabel("Опис групи товарів: ");
        typeDescription.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2  , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        typeDescription.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeDescription);

        typeProducer = new JLabel("Виробник: ");
        typeProducer.setBounds(WIDTH_OF_FRAME /2 - 270, HEIGHT_OF_FRAME /2 - 40 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        typeProducer.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeProducer);
        typeProducer.setVisible(false);

        typeCount = new JLabel("Кількість на складі: ");
        typeCount.setBounds(WIDTH_OF_FRAME /2 + WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - 40 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        typeCount.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeCount);
        typeCount.setVisible(false);

        typePrice = new JLabel("Ціна за одиницю: ");
        typePrice.setBounds(WIDTH_OF_FRAME /2 - 55, HEIGHT_OF_FRAME /2  + 60, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        typePrice.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typePrice);
        typePrice.setVisible(false);


        nameOfGroup = new JTextField();
        nameOfGroup.setHorizontalAlignment(JTextField.CENTER);
        nameOfGroup.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        nameOfGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfGroup);

        descriptionOfGroup = new JTextField();
        descriptionOfGroup.setHorizontalAlignment(JTextField.CENTER);
        descriptionOfGroup.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 + HEIGHT_OF_FIELD, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        descriptionOfGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(descriptionOfGroup);

        nameOfProduct = new JTextField();
        nameOfProduct.setHorizontalAlignment(JTextField.CENTER);
        nameOfProduct.setBounds(WIDTH_OF_FRAME /2 - 350, HEIGHT_OF_FRAME /2 - 90 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        nameOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfProduct);
        nameOfProduct.setVisible(false);

        descriptionOfProduct = new JTextField();
        descriptionOfProduct.setHorizontalAlignment(JTextField.CENTER);
        descriptionOfProduct.setBounds(WIDTH_OF_FRAME /2 + 50, HEIGHT_OF_FRAME /2 - 90, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        descriptionOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(descriptionOfProduct);
        descriptionOfProduct.setVisible(false);

        producerOfProduct = new JTextField();
        producerOfProduct.setHorizontalAlignment(JTextField.CENTER);
        producerOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        producerOfProduct.setBounds(WIDTH_OF_FRAME /2 - 350, 365 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        this.getContentPane().add(producerOfProduct);
        producerOfProduct.setVisible(false);

        countOfProduct = new JTextField();
        countOfProduct.setHorizontalAlignment(JTextField.CENTER);
        countOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        countOfProduct.setBounds(WIDTH_OF_FRAME /2 + 50, 365, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        this.getContentPane().add(countOfProduct);
        countOfProduct.setVisible(false);

        priceOfProduct = new JTextField();
        priceOfProduct.setHorizontalAlignment(JTextField.CENTER);
        priceOfProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        priceOfProduct.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 460, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        this.getContentPane().add(priceOfProduct);
        priceOfProduct.setVisible(false);

        groupNameBox = new JComboBox<>();
        groupNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 130 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        groupNameBox.setRenderer(renderer);
        groupNameBox.setFont(font.deriveFont(Font.BOLD, 16f));
        groupNameBox.setVisible(false);
        this.getContentPane().add(groupNameBox);

        saveButton = new JButton("Зберегти");
        saveButton.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_BUTTON /2, HEIGHT_OF_FRAME - 150, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        saveButton.setBackground(new Color(102, 153, 102, 250));
        saveButton.setForeground(Color.WHITE);
        Font buttonFont = saveButton.getFont();
        saveButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(saveButton);

        saveButton.addActionListener(e -> {
            saveButtonActionPerformed();
        });
    }

    private void functionActionPerformed() {
        if (chooseFunction.getSelectedIndex() != 2) {
            if (chooseSubject.getSelectedIndex() == 0) {

                groupNameBox.setVisible(false);
                nameOfProduct.setVisible(false);
                descriptionOfProduct.setVisible(false);
                producerOfProduct.setVisible(false);
                countOfProduct.setVisible(false);
                priceOfProduct.setVisible(false);

                typeProducer.setVisible(false);
                typeCount.setVisible(false);
                typePrice.setVisible(false);

                typeDescription.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2  , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                typeName.setText("Назва групи товарів:");
                typeName.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD *2 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);

                descriptionOfGroup.setVisible(true);
                typeDescription.setVisible(true);
                nameOfGroup.setVisible(true);

            } else {
                nameOfGroup.setVisible(false);
                descriptionOfGroup.setVisible(false);

                typeName.setText("Назва товару:");
                typeName.setBounds(WIDTH_OF_FRAME /2 - 270, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD *2 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);

                typeDescription.setVisible(true);
                typeDescription.setText("Опис товару:");
                typeDescription.setBounds(WIDTH_OF_FRAME /2 + WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD *2 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);

                groupNameBox.setVisible(true);
                nameOfProduct.setVisible(true);
                descriptionOfProduct.setVisible(true);
                producerOfProduct.setVisible(true);
                countOfProduct.setVisible(true);
                priceOfProduct.setVisible(true);

                typeProducer.setVisible(true);
                typeCount.setVisible(true);
                typePrice.setVisible(true);
            }
        } else {
            if (chooseSubject.getSelectedIndex() == 0) {

                groupNameBox.setVisible(false);
                nameOfProduct.setVisible(false);
                descriptionOfProduct.setVisible(false);
                producerOfProduct.setVisible(false);
                countOfProduct.setVisible(false);
                priceOfProduct.setVisible(false);
                descriptionOfGroup.setVisible(false);

                typeProducer.setVisible(false);
                typeCount.setVisible(false);
                typePrice.setVisible(false);
                typeDescription.setVisible(false);

                typeName.setText("Назва групи товарів:");
                typeName.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD *2 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);

                nameOfGroup.setVisible(true);

            } else {
                groupNameBox.setVisible(false);
                nameOfProduct.setVisible(false);
                descriptionOfProduct.setVisible(false);
                producerOfProduct.setVisible(false);
                countOfProduct.setVisible(false);
                priceOfProduct.setVisible(false);
                descriptionOfGroup.setVisible(false);

                typeProducer.setVisible(false);
                typeCount.setVisible(false);
                typePrice.setVisible(false);
                typeDescription.setVisible(false);

                typeName.setText("Назва товару:");
                typeName.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD *2 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);

                nameOfGroup.setVisible(true);
            }
        }
    }

    private void saveButtonActionPerformed() {
        if (chooseSubject.getSelectedIndex() == 0) {
            String groupName = nameOfGroup.getText();
            String groupDescription = descriptionOfGroup.getText();

            if (!groupName.trim().isEmpty() && !groupDescription.trim().isEmpty()) {
                addGroup(groupName, groupDescription);
                nameOfGroup.setText("");
                descriptionOfGroup.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Групу НЕ додано. Заповніть правильно всі поля!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            String groupName = groupNameBox.getSelectedItem().toString();
            String name = nameOfProduct.getText();
            String description = descriptionOfProduct.getText();
            String producer = producerOfProduct.getText();
            int count = 0;
            int price = 0;
            try {
                count = parseInt(countOfProduct.getText());
                price = parseInt(priceOfProduct.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Кількість та ціна повинні бути числами!", "Error", JOptionPane.ERROR_MESSAGE);
            }

            if (!name.trim().isEmpty() && !description.trim().isEmpty() && !producer.trim().isEmpty() && count >= 0 && price > 0) {
                addProductToGroup(groupName, name, description, producer, count, price);
                nameOfProduct.setText("");
                descriptionOfProduct.setText("");
                producerOfProduct.setText("");
                countOfProduct.setText("");
                priceOfProduct.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Товар НЕ додано. Заповніть правильно всі поля!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addGroup(String groupName, String description) {
        if (isGroupUnique(groupName)) {
            GroupOfItems group = new GroupOfItems(groupName, description);
            //Main.groupsList.add(group);

            try {
                FileWriter writer = new FileWriter(main.groupsFileName, true);
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
            //productsList.add(item);
            try {
                // Зберігання товару в файлі групи
                File groupFile = new File(groupName + ".txt");
                FileWriter writer = new FileWriter(groupFile, true);
                writer.write(name + "," + description + "," + producer + "," + count + "," + pricePerOne + "\n");
                writer.close();

                // Збереження товару в файлі "AllProducts.txt"
                FileWriter allProductsWriter = new FileWriter(main.allProductsFileName, true);
                allProductsWriter.write(name + "," + description + "," + producer + "," + count + "," + pricePerOne + "\n");
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


    private void updateInformation() {
        try {
            File file = new File(main.groupsFileName);
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
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні груп товарів.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isGroupUnique(String groupName) {
        try {
            File groupFile = new File(main.groupsFileName);
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
}
