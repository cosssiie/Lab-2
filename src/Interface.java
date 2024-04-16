import javax.swing.*;
import java.awt.*;

public class Interface extends JFrame{
    private final Main main;

    private JComboBox<String> choose;
    private JButton okButton;

    private static final int WIDTH_OF_FRAME = 700;
    private static final int HEIGHT_OF_FRAME = 500;
    private static final int WIDTH_OF_FIELD = 450;
    private static final int HEIGHT_OF_FIELD = 70;
    private static final int WIDTH_OF_BUTTON = 170;
    private static final int HEIGHT_OF_BUTTON = 70;

    public Interface(String name, Main main) {
        this.main = main;
        this.setTitle(name);
        this.setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        this.getContentPane().setLayout(null);
        this.getContentPane().setBackground(new Color(204, 255, 153, 250));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void start(){
        init();
        this.setVisible(true);
    }

    private void init() {
        if (choose == null){
            choose = new JComboBox<>();
            choose.addItem("Додати/Редагувати/Видалити товар/групу товарів");
            choose.addItem("Постачання/списання товару");
            choose.addItem("Пошук товару");
            choose.addItem("Статистика");
            DefaultListCellRenderer renderer = new DefaultListCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            choose.setRenderer(renderer);
            Font font = choose.getFont();
            choose.setFont(font.deriveFont(Font.BOLD, 16f));
            choose.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_FIELD /2, HEIGHT_OF_FRAME /2 - HEIGHT_OF_FIELD*2, WIDTH_OF_FIELD, HEIGHT_OF_FIELD);
            add(choose);

            okButton = new JButton("OK");
            okButton.setBounds(WIDTH_OF_FRAME /2 - WIDTH_OF_BUTTON /2, (int) (HEIGHT_OF_FRAME*0.8 - HEIGHT_OF_BUTTON), WIDTH_OF_BUTTON, HEIGHT_OF_BUTTON);
            okButton.setBackground(new Color(102, 153, 102, 250));
            okButton.setForeground(Color.WHITE);
            Font buttonFont = okButton.getFont();
            okButton.setFont(buttonFont.deriveFont(Font.BOLD, 16f));
            add(okButton);

            okButton.addActionListener(e -> {
                okButtonActionPerformed();
            });
        }
    }

    private void okButtonActionPerformed() {
        switch (choose.getSelectedIndex()){
            case 0:
                main.editProducts.start();
                break;
            case 1:
                main.supplyOfProducts.start();
                break;
            case 2:
                main.productSearch.start();
                break;
            case 3:
                main.statistics.start();
                break;
        }
    }
}
