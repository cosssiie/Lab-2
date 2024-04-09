import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class AddProducts extends JFrame {

    /**Масив для зберігання груп товарів */
    private ArrayList<GroupOfItems> groupsList = new ArrayList<>();

    /**Вибір функції: додати групу товарів/додати товар */
    private JComboBox<String> chooseFunction;

    /**Ввести назву товару/ групи товарів */
    private JTextField nameOfGroup;

    /**Ввести опис групи товарів */
    private JTextField descriptionOfGroup;

    /**Вибір групи товарів, куди додати товар */
    private JComboBox<String> groupNameBox;

    /**Кнопка "зберегти" */
    private JButton saveButton;

    /**Напис назви групи*/
    private JLabel typeName;

    /**Напис опису групи*/
    private JLabel typeDescription;


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
                descriptionOfGroup.setVisible(false);
                typeDescription.setVisible(false);
                nameOfGroup.setBounds(width/2 - widthOfField/2, height/2 + heightOfField , widthOfField, heightOfField);
                typeName.setText("Назва групи товарів:");
                typeName.setBounds(width/2 - widthOfField/2, height/2 + heightOfField/4 , widthOfField, heightOfField);

            } else {
                groupNameBox.setVisible(false);
                descriptionOfGroup.setVisible(true);
                descriptionOfGroup.setBounds(width/2 - widthOfField/2, height/2 + heightOfField , widthOfField, heightOfField);
                typeDescription.setVisible(true);
                typeDescription.setBounds(width/2 - widthOfField/2, height/2 + heightOfField/4 , widthOfField, heightOfField);
                nameOfGroup.setBounds(width/2 - widthOfField/2, height/2 - heightOfField , widthOfField, heightOfField);
                typeName.setBounds(width/2 - widthOfField/2, height/2 - heightOfField*2 , widthOfField, heightOfField);


            }
        });

        typeName = new JLabel();
        typeName.setText("Назва групи товарів: ");
        typeName.setBounds(width/2 - widthOfField/2, 175 , widthOfField, heightOfField);
        typeName.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeName);

        typeDescription = new JLabel();
        typeDescription.setText("Опис групи товарів: ");
        typeDescription.setBounds(width/2 - widthOfField/2, 280 , widthOfField, heightOfField);
        typeDescription.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(typeDescription);


        nameOfGroup = new JTextField();
        nameOfGroup.setBounds(width/2 - widthOfField/2, height/2 - heightOfField , widthOfField, heightOfField);
        nameOfGroup.setHorizontalAlignment(JTextField.CENTER);
        nameOfGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(nameOfGroup);

        descriptionOfGroup = new JTextField();
        descriptionOfGroup.setBounds(width/2 - widthOfField/2, height/2 + heightOfField/2 , widthOfField, heightOfField);
        descriptionOfGroup.setHorizontalAlignment(JTextField.CENTER);
        descriptionOfGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        this.getContentPane().add(descriptionOfGroup);

        groupNameBox = new JComboBox<>();
        groupNameBox.setBounds(width/2 - widthOfField/2, 215 , widthOfField, heightOfField);
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
        } else if (chooseFunction.getSelectedIndex() == 1) {
            String groupName = (String) groupNameBox.getSelectedItem();
            String description = descriptionOfGroup.getText();
            if (!groupName.isEmpty() && !description.isEmpty()) {
                addGroup(groupName, description);
                nameOfGroup.setText("");
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
            JOptionPane.showMessageDialog(this, "Група товарів \"" + groupName + "\" успішно додана!");

            groupNameBox.addItem(groupName);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Помилка при додаванні групи товарів.");
        }
    }

//    private void addProductToGroup(String groupName, String productName) {
//        try {
//            File groupFile = new File(groupName + ".txt");
//            FileWriter writer = new FileWriter(groupFile, true);
//            writer.write(productName + "\n");
//            writer.close();
//            JOptionPane.showMessageDialog(this, "Товар \"" + productName + "\" успішно доданий в групу \"" + groupName + "\"!");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(this, "Помилка при додаванні товару в групу.");
//        }
//    }

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
