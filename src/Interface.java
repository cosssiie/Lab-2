import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public class Interface extends JFrame{

    // Реалізувати інтерфейс додавання товару
    // Інтерфейс списання товару
    // Пошук товару.
    
    private JComboBox<String> choose;

    private static final int width = 500;
    private static final int height = 500;
    private static final int widthOfButton = 150;
    private static final int heightOfButton = 60;

    public Interface(String name) {
        super();
        this.setTitle(name);
        this.setSize(width, height);
        this.getContentPane().setLayout(null);
        init();
        this.add(choose);
       
    }

    private void init() {

        if (choose == null){
           choose = new JComboBox<>(); 
           choose.addItem("Додати товар / групу товарів");
           choose.addItem("Видалити товар / групу товарів");
           choose.addItem("Пошук товару");
           choose.setBounds(0, 0, 150, 30); 
           this.getContentPane().add(choose); 
        }
    }

    public static void main(String[] args) {
        Interface stock = new Interface("Stock");
        stock.setVisible(true);
    }

}
