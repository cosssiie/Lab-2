import javax.swing.*;
import java.awt.*;

public class Statistics extends JDialog {
    private final Main main;

    private JLabel label;
    private JComboBox<String> statFuncBox;
    private JComboBox<String> groupNameBox;
    private JTextArea statisticsArea;
    private JScrollPane scrollPane;

    private static final int WIDTH_OF_FRAME = 800;
    private static final int HEIGHT_OF_FRAME = 700;
    private static final int WIDTH_OF_FIELD = 300;
    private static final int HEIGHT_OF_FIELD = 70;
    private static final int WIDTH_OF_AREA = 610;
    private static final int HEIGHT_OF_AREA = 400;

    private volatile boolean isPrinting = false;

    public Statistics(String name, Main main, JFrame parent) {
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
        this.setVisible(true);
    }

    private void init() {
        label = new JLabel("Статистика");
        label.setBounds(WIDTH_OF_FRAME / 2 - 50, 40, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        Font font = label.getFont();
        label.setFont(font.deriveFont(Font.BOLD, 16f));
        add(label);

        statisticsArea = new JTextArea();
        statisticsArea.setFont(font.deriveFont(Font.BOLD, 16f));
        statisticsArea.setEditable(false);
        scrollPane = new JScrollPane(statisticsArea);
        scrollPane.setBounds(WIDTH_OF_FRAME / 2 - WIDTH_OF_AREA / 2, 200, WIDTH_OF_AREA, HEIGHT_OF_AREA);
        add(scrollPane);

        statFuncBox = new JComboBox<>();
        statFuncBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 120 , WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
        statFuncBox.setRenderer(renderer);
        statFuncBox.setFont(font.deriveFont(Font.BOLD, 16f));
        statFuncBox.addItem("Загальна статистика");
        statFuncBox.addItem("Статистика за групою товарів");
        add(statFuncBox);
        statFuncBox.addActionListener(e -> {
            updateGroupList();
            printStatistics();
        });

        groupNameBox = new JComboBox<>();
        groupNameBox.setBounds(WIDTH_OF_FRAME /2 + 5, 120, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
        groupNameBox.setRenderer(renderer);
        groupNameBox.setFont(font.deriveFont(Font.BOLD, 16f));
        groupNameBox.setVisible(false);
        this.getContentPane().add(groupNameBox);
        groupNameBox.addActionListener(e -> {
            printStatistics();
        });
    }

    private void updateGroupList() {
        groupNameBox.removeAllItems();
        for (GroupOfItems group : main.getGroupsList()){
            groupNameBox.addItem(group.getNameOfGroup());
        }
    }

    private void printStatistics() {
        if (isPrinting){
            return;
        }
        isPrinting = true;
        if(statFuncBox.getSelectedIndex() == 1){
            statFuncBox.setBounds(WIDTH_OF_FRAME /2 - 5 - WIDTH_OF_FIELD, 120, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
            groupNameBox.setVisible(true);
        } else {
            statFuncBox.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, 120, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
            groupNameBox.setVisible(false);
        }

        statisticsArea.setText("");
        if (statFuncBox.getSelectedIndex() == 0){
            statisticsArea.append("Загальна вартість товарів на складі: " + main.getTotalStockValue() + "\n");
            statisticsArea.append("Ім'я товару - Кількість - Ціна за одиницю - Виробник - Опис\n");
            statisticsArea.append("----------------------------------------------------------------------------------------------------------------\n");
            for (GroupOfItems group : main.getGroupsList()){
                for (Items product : group.productsList){
                    statisticsArea.append(product.getName() + "    -    " + product.getCount() + " шт.    -    " + product.getPricePerOne() + " грн.    -    " + product.getProducer() + "    -    " + product.getDescription() + "\n");
                }
            }
        } else {
            if(groupNameBox.getSelectedItem() != null){
                GroupOfItems group = main.getGroupByName(groupNameBox.getSelectedItem().toString());
                statisticsArea.append("Загальна вартість товарів групи " + group.getNameOfGroup() + " на складі: " + group.getTotalValue() + "\n");
                statisticsArea.append("Ім'я товару - Кількість - Ціна за одиницю - Виробник - Опис\n");
                statisticsArea.append("----------------------------------------------------------------------------------------------------------------\n");
                for (Items product : group.productsList){
                    statisticsArea.append(product.getName() + "    -    " + product.getCount() + " шт.    -    " + product.getPricePerOne() + " грн.    -    " + product.getProducer() + "    -    " + product.getDescription() + "\n");
                }
            }
        }
        isPrinting = false;
    }
}
