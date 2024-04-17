import javax.swing.*;
import java.awt.*;

public class SupplyOfProducts extends JDialog {
    private final Main main;

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

    /** Напис "Поточна кількість товару" */
    private JLabel currentQuantityLabel;

    private static final int WIDTH_OF_FRAME = 500;
    private static final int HEIGHT_OF_FRAME = 700;
    private static final int WIDTH_OF_FIELD = 300;
    private static final int HEIGHT_OF_FIELD = 70;
    private static final int WIDTH_OF_BUTTON = 170;
    private static final int HEIGHT_OF_BUTTON = 70;

    private volatile boolean isUpdating = false;

    public SupplyOfProducts(String name, Main main, JFrame parent) {
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
        if (chooseFunction != null){
            return;
        }
        chooseFunction = new JComboBox<>();
        chooseFunction.addItem("Списати товар");
        chooseFunction.addItem("Доставити товар");
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        chooseFunction.setRenderer(renderer);
        Font font = chooseFunction.getFont();
        chooseFunction.setFont(font.deriveFont(Font.BOLD, 16f));
        chooseFunction.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, HEIGHT_OF_FRAME / 2 - 300, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        add(chooseFunction);

        chooseGroup = new JComboBox<>();
        chooseGroup.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, HEIGHT_OF_FRAME / 2 - 200, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        chooseGroup.setRenderer(renderer);
        chooseGroup.setFont(font.deriveFont(Font.BOLD, 16f));
        add(chooseGroup);

        chooseProduct = new JComboBox<>();
        chooseProduct.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, HEIGHT_OF_FRAME / 2 - 100, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        chooseProduct.setRenderer(renderer);
        chooseProduct.setFont(font.deriveFont(Font.BOLD, 16f));
        add(chooseProduct);

        supplyCountLabel = new JLabel("Скільки списати:");
        supplyCountLabel.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, HEIGHT_OF_FRAME - 320, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        supplyCountLabel.setFont(font.deriveFont(Font.BOLD, 16f));
        add(supplyCountLabel);

        currentQuantityLabel = new JLabel("Поточна кількість товару: ");
        currentQuantityLabel.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, HEIGHT_OF_FRAME - 360, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        currentQuantityLabel.setFont(font.deriveFont(Font.BOLD, 16f));
        add(currentQuantityLabel);

        supplyCount = new JTextField();
        supplyCount.setHorizontalAlignment(JTextField.CENTER);
        supplyCount.setFont(font.deriveFont(Font.BOLD, 16f));
        supplyCount.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_FIELD / 2, 450, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        add(supplyCount);

        JButton saveButton = new JButton("OK");
        saveButton.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_BUTTON / 2, HEIGHT_OF_FRAME - 150, WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
        saveButton.setBackground(new Color(102, 153, 102, 250));
        saveButton.setForeground(Color.WHITE);
        Font buttonFont = saveButton.getFont();
        saveButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
        add(saveButton);

        saveButton.addActionListener(e -> {
            saveButtonActionPerformed();
        });


        chooseFunction.addActionListener(e -> {
            if (chooseFunction.getSelectedIndex() == 0) {
                supplyCountLabel.setText("Скільки списати:");

            } else {
                supplyCountLabel.setText("Скільки доставити:");
            }
        });

        chooseGroup.addActionListener(e -> {
            updateProductNameBox();
        });

        chooseProduct.addActionListener(e -> {
            showQuantityOfProduct();
        });
    }

    private void showQuantityOfProduct() {
        String selectedGroup;
        String selectedProduct;
        if (chooseGroup.getSelectedItem() != null && chooseProduct.getSelectedItem() != null) {
            selectedGroup = chooseGroup.getSelectedItem().toString();
            selectedProduct = chooseProduct.getSelectedItem().toString();
        } else {
            return;
        }
        currentQuantityLabel.setText("Поточна кількість товару: " + main.getGroupByName(selectedGroup).getProductByName(selectedProduct).getCount());
        System.out.println("showQuantityOfProduct");
    }

    private void saveButtonActionPerformed() {
        String selectedGroup;
        String selectedProduct;
        if (chooseGroup.getSelectedItem() != null) {
            selectedGroup = chooseGroup.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Виберіть групу товару.", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (chooseProduct.getSelectedItem() != null) {
            selectedProduct = chooseProduct.getSelectedItem().toString();
        } else {
            JOptionPane.showMessageDialog(this, "Виберіть товар.", "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (chooseFunction.getSelectedIndex() == 0) {
                main.getGroupByName(selectedGroup).subtractProduct(selectedProduct, supplyCount.getText());
                main.updateFiles();
                JOptionPane.showMessageDialog(this, "Товар успішно списано.");
                showQuantityOfProduct();
            } else {
                main.getGroupByName(selectedGroup).supplyProduct(selectedProduct, supplyCount.getText());
                main.updateFiles();
                JOptionPane.showMessageDialog(this, "Товар успішно доставлено.");
                showQuantityOfProduct();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
            return;
        } finally {
            supplyCount.setText("");
        }
    }

    private void updateInformation() {
        if (isUpdating) {
            return;
        }
        isUpdating = true;
        System.out.println("Updating information");
        chooseGroup.removeAllItems();
        for (GroupOfItems group : main.groupsList) {
            chooseGroup.addItem(group.getNameOfGroup());
        }
        isUpdating = false;
    }

    private void updateProductNameBox() {
        chooseProduct.removeAllItems();
        GroupOfItems selectedGroup;
        if (chooseGroup.getSelectedItem() != null) {
            selectedGroup = main.getGroupByName(chooseGroup.getSelectedItem().toString());
            for (Items product : selectedGroup.productsList) {
                chooseProduct.addItem(product.getName());
            }
        }
    }
}