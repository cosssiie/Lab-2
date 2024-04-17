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

    /**Вибір товару при видаленні */
    public JComboBox<String> productNameBox;

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

    private volatile boolean isUpdating = false;

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
        if (chooseSubject != null){
            return;
        }
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
        groupNameBox.addActionListener(e -> {
            updateProductNameBox();
        });

        productNameBox = new JComboBox<>();
        productNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 140 + HEIGHT_OF_FIELD, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        productNameBox.setRenderer(renderer);
        productNameBox.setFont(font.deriveFont(Font.BOLD, 16f));
        productNameBox.setVisible(false);
        this.getContentPane().add(productNameBox);

        saveButton = new JButton("OK");
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

    private void updateProductNameBox() {
        productNameBox.removeAllItems();
        GroupOfItems selectedGroup;
        if (groupNameBox.getSelectedItem() != null) {
            selectedGroup = main.getGroupByName(groupNameBox.getSelectedItem().toString());
            for (Items product : selectedGroup.productsList) {
                productNameBox.addItem(product.getName());
            }
        }
    }

    private void functionActionPerformed() {
        if (chooseFunction.getSelectedIndex() != 2) {
            if (chooseSubject.getSelectedIndex() == 0) {
                productNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 140 + HEIGHT_OF_FIELD, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                groupNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 130 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                productNameBox.setVisible(false);
                if (chooseFunction.getSelectedIndex() == 0){
                    groupNameBox.setVisible(false);
                } else {
                    groupNameBox.setVisible(true);
                }
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
                typeName.setVisible(true);
            } else {
                nameOfGroup.setVisible(false);
                descriptionOfGroup.setVisible(false);
                if(chooseFunction.getSelectedIndex() == 1){
                    groupNameBox.setBounds(WIDTH_OF_FRAME /2 - 10 - WIDTH_OF_FIELD, 130 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                    productNameBox.setBounds(WIDTH_OF_FRAME /2 + 10, 130 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                    productNameBox.setVisible(true);
                } else {
                    productNameBox.setVisible(false);
                    productNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 140 + HEIGHT_OF_FIELD, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                    groupNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 130 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
                }
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
                typeName.setVisible(true);
            }
        } else {
            productNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 140 + HEIGHT_OF_FIELD, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
            groupNameBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 130 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
            if (chooseSubject.getSelectedIndex() == 0) {

                productNameBox.setVisible(false);
                nameOfProduct.setVisible(false);
                descriptionOfProduct.setVisible(false);
                producerOfProduct.setVisible(false);
                countOfProduct.setVisible(false);
                priceOfProduct.setVisible(false);
                descriptionOfGroup.setVisible(false);
                nameOfGroup.setVisible(false);

                typeProducer.setVisible(false);
                typeCount.setVisible(false);
                typePrice.setVisible(false);
                typeDescription.setVisible(false);
                typeName.setVisible(false);

                groupNameBox.setVisible(true);
            } else {
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
                typeName.setVisible(false);

                groupNameBox.setVisible(true);
                productNameBox.setVisible(true);
            }
        }
    }

    private void saveButtonActionPerformed() {
        if (chooseFunction.getSelectedIndex() == 0){
            if(chooseSubject.getSelectedIndex() == 0){
                addGroup();
            } else {
                addProduct();
            }
        } else if (chooseFunction.getSelectedIndex() == 1){
            if(chooseSubject.getSelectedIndex() == 0){
                editGroup();
            } else {
                editProduct();
            }
        } else {
            if(chooseSubject.getSelectedIndex() == 0){
                deleteGroup();
            } else {
                deleteProduct();
            }
        }
        nameOfGroup.setText("");
        descriptionOfGroup.setText("");
        nameOfProduct.setText("");
        descriptionOfProduct.setText("");
        producerOfProduct.setText("");
        countOfProduct.setText("");
        priceOfProduct.setText("");
    }

    private void deleteProduct() {
        String groupName;
        if(groupNameBox.getSelectedItem() != null){
            groupName = groupNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Групу товарів не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String productName;
        if(productNameBox.getSelectedItem() != null){
            productName = productNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Товар не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            main.getGroupByName(groupName).removeProduct(productName);
            main.updateFiles();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateProductNameBox();
        JOptionPane.showMessageDialog(this, "Товар \"" + productName + "\" успішно видалено з групи \"" + groupName + "\"!");
    }

    private void deleteGroup() {
        String groupName;
        if(groupNameBox.getSelectedItem() != null){
            groupName = groupNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Групу товарів не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            main.removeGroup(groupName);
            main.updateFiles();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateInformation();
        JOptionPane.showMessageDialog(this, "Групу товарів \"" + groupName + "\" успішно видалено!");
    }

    private void editProduct() {
        String groupName;
        if(groupNameBox.getSelectedItem() != null){
            groupName = groupNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Групу товарів не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String productName;
        if(productNameBox.getSelectedItem() != null){
            productName = productNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Товар не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String newName = nameOfProduct.getText();
        newName = newName.replaceAll(";", "");
        String newDescription = descriptionOfProduct.getText();
        newDescription = newDescription.replaceAll(";", "");
        String newProducer = producerOfProduct.getText();
        newProducer = newProducer.replaceAll(";", "");
        String newCount = countOfProduct.getText();
        String newPrice = priceOfProduct.getText();
        try {
            main.getGroupByName(groupName).editProduct(productName, newName, newDescription, newProducer, newCount, newPrice);
            main.updateFiles();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateProductNameBox();
        JOptionPane.showMessageDialog(this, "Товар \"" + productName + "\" успішно відредаговано!");
    }

    private void editGroup() {
        String oldName;
        if(groupNameBox.getSelectedItem() != null){
            oldName = groupNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Групу товарів не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String newName = nameOfGroup.getText();
        newName = newName.replaceAll(";", "");
        String newDescription = descriptionOfGroup.getText();
        newDescription = newDescription.replaceAll(";", "");
        try {
            main.editGroup(oldName, newName, newDescription);
            main.updateFiles();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateInformation();
        JOptionPane.showMessageDialog(this, "Групу товарів \"" + oldName + "\" успішно відредаговано!");
    }

    private void addProduct() {
        String groupName;
        if(groupNameBox.getSelectedItem() != null){
            groupName = groupNameBox.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Групу товарів не обрано!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String name = nameOfProduct.getText();
        name = name.replaceAll(";", "");
        String description = descriptionOfProduct.getText();
        description = description.replaceAll(";", "");
        String producer = producerOfProduct.getText();
        producer = producer.replaceAll(";", "");
        int count = 0;
        int price = 0;
        try {
            count = parseInt(countOfProduct.getText());
            price = parseInt(priceOfProduct.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Кількість та ціна повинні бути числами!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            main.getGroupByName(groupName).addProduct(name, description, producer, count, price);
            main.updateFiles();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateInformation();
        JOptionPane.showMessageDialog(this, "Товар \"" + name + "\" успішно додано в групу \"" + groupName + "\"!");
    }

    private void addGroup() {
        String groupName = nameOfGroup.getText();
        groupName = groupName.replaceAll(";", "");
        String groupDescription = descriptionOfGroup.getText();
        groupName = groupName.replaceAll(";", "");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(main.groupsFileName, true))) {
            main.addGroup(new GroupOfItems(groupName, groupDescription, main));
            writer.write(groupName + ";" + groupDescription + "\n");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при додаванні групи товарів у файл.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        updateInformation();
        JOptionPane.showMessageDialog(this, "Групу товарів \"" + groupName + "\" успішно додано!");
    }

    private void addProductToGroup(String groupName, String name, String description, String producer, int count, int pricePerOne) {

    }

    private void updateInformation() {
        if (isUpdating) {
            return;
        }
        isUpdating = true;
        System.out.println("Updating information");
        groupNameBox.removeAllItems();
        for (GroupOfItems group : main.groupsList) {
            groupNameBox.addItem(group.getNameOfGroup());
        }
        isUpdating = false;
    }

}
