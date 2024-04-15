import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Лабораторна робота №2
 * Автоматизоване робоче місце
 *
 * @author Гапоненко Єлизавета Артемівна
 * @author Розумєй Максим Віталійович
 */
public class Main {
    /**Масив для зберігання груп товарів */
    public ArrayList<GroupOfItems> groupsList = new ArrayList<>();
    //вікна програми
    Interface mainInterface;
    ProductSearch productSearch;
    EditProducts editProducts;
    SupplyOfProducts supplyOfProducts;
    Statistics statistics;

    public final String groupsFileName; //файл для збереження груп товарів
    public final String allProductsFileName; //файл для збереження груп товарів

    public static void main(String[] args) {
        Main main = new Main("GroupsOfProducts.txt", "AllProducts.txt");
        main.start();
    }

    public Main(String groupsFileName, String allProductsFileName) {
        this.groupsFileName = groupsFileName;
        this.allProductsFileName = allProductsFileName;
    }

    private void start() {
        loadInformationFromFiles();
//        for (GroupOfItems group : groupsList){
//            System.out.println(group);
//            for (Items product : group.productsList){
//                System.out.println(product);
//            }
//        }
        mainInterface = new Interface("Stock", this);
        productSearch = new ProductSearch("Пошук товару");
        editProducts = new EditProducts("Додавання товару", this, mainInterface);
        //supplyOfProducts = new SupplyOfProducts("Поставка товару");
        //statistics = new Statistics("Статистика");
        mainInterface.start();
    }

    private void loadInformationFromFiles() {
        try(BufferedReader reader = new BufferedReader(new FileReader(groupsFileName))){
            String line;
            while ((line = reader.readLine()) != null){
                groupsList.add(new GroupOfItems(line.split(";")[0], line.split(";")[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка при зчитуванні файлу груп товарів.", "Помилка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(allProductsFileName))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] productData = line.split(";");
                for (GroupOfItems group : groupsList){
                    if (group.getNameOfGroup().equals(productData[0])){
                        group.addProduct(new Items(productData[1], productData[2],productData[3], Integer.parseInt(productData[4]), Integer.parseInt(productData[5])));
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка при зчитуванні файлу усіх товарів.", "Помилка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}