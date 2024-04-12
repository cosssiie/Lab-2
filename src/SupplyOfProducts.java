import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            String selectedGroup = (String) (chooseGroup.getSelectedItem() + ".txt");
            loadItemsForSelectedGroup(selectedGroup);
        });
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
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні товарів для обраної групи.");
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
            JOptionPane.showMessageDialog(this, "Помилка при завантаженні імен груп товарів.");
        }
    }


    private void saveButtonActionPerformed() {

    }

    public static void main(String[] args) {
        SupplyOfProducts supply = new SupplyOfProducts("Поставка та списання товарів", AddProducts.groupsList);
        supply.setVisible(true);
    }
}