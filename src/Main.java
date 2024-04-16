import javax.swing.*;
import java.io.*;
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
        for (GroupOfItems group : groupsList){
            System.out.println(group.getNameOfGroup()+"-----------");
            for (Items product : group.productsList){
                System.out.println(product.getName());
            }
        }
        mainInterface = new Interface("Stock", this);
        editProducts = new EditProducts("Додавання товару", this, mainInterface);
        productSearch = new ProductSearch("Пошук товару", this, mainInterface);
        supplyOfProducts = new SupplyOfProducts("Поставка товару", this, mainInterface);
        //statistics = new Statistics("Статистика");
        mainInterface.start();
    }

    private void loadInformationFromFiles() {
        try(BufferedReader reader = new BufferedReader(new FileReader(groupsFileName))){
            String line;
            while ((line = reader.readLine()) != null){
                groupsList.add(new GroupOfItems(line.split(";")[0], line.split(";")[1]));
            }
        } catch (Exception e) {
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
                        group.addProduct(productData[1], productData[2],productData[3], Integer.parseInt(productData[4]), Integer.parseInt(productData[5]));
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
    public GroupOfItems getGroupByName(String name){
        for (GroupOfItems group : groupsList){
            if (group.getNameOfGroup().equals(name)){
                return group;
            }
        }
        return null;
    }
    public boolean groupExists(String name){
        for (GroupOfItems group : groupsList){
            if (group.getNameOfGroup().equals(name)){
                return true;
            }
        }
        return false;
    }
    public void addGroup(GroupOfItems group) throws IllegalArgumentException{
        if (groupExists(group.getNameOfGroup())){
            throw new IllegalArgumentException("Група з такою назвою вже існує.");
        } else if (group.getNameOfGroup().trim().isEmpty()){
            throw new IllegalArgumentException("Назва групи не може бути порожньою.");
        } else if (group.getGroupDescription().trim().isEmpty()){
            throw new IllegalArgumentException("Опис групи не може бути порожнім.");
        }
        groupsList.add(group);
    }

    public void editGroup(String oldName, String newName, String newDescription) throws IllegalArgumentException{
        if (!groupExists(oldName)){
            throw new IllegalArgumentException("Групи з такою назвою не існує.");
        } else if (groupExists(newName) && newDescription.trim().isEmpty()){
            throw new IllegalArgumentException("Група має таку саму назву");
        } else if (newName.trim().isEmpty() && newDescription.trim().isEmpty()){
            throw new IllegalArgumentException("Нова назва або опис повинні бути заповнені.");
        } else if (newName.equals(oldName) && newDescription.equals(getGroupByName(oldName).getGroupDescription())){
            throw new IllegalArgumentException("Нова назва або опис повинні бути відмінні від старих.");
        } else if (newName.trim().isEmpty() && getGroupByName(oldName).getGroupDescription().equals(newDescription)){
            throw new IllegalArgumentException("Група має такий самий опис.");
        }
        for (GroupOfItems group : groupsList) {
            if (group.getNameOfGroup().equals(oldName)) {
                if (!newName.trim().isEmpty()) {
                    group.setNameOfGroup(newName);
                }
                if (!newDescription.trim().isEmpty()) {
                    group.setGroupDescription(newDescription);
                }
                break;
            }
        }
    }

    public void removeGroup(String name) throws IllegalArgumentException{
        if (!groupExists(name)){
            throw new IllegalArgumentException("Групи з такою назвою не існує.");
        }
        for (GroupOfItems group : groupsList){
            if (group.getNameOfGroup().equals(name)){
                groupsList.remove(group);
                break;
            }
        }
    }

    public void updateFiles(){
        try {
            BufferedWriter writer1 = new BufferedWriter(new FileWriter(groupsFileName));
            for (GroupOfItems group : groupsList){
                writer1.write(group.getNameOfGroup() + ";" + group.getGroupDescription());
                writer1.newLine();
            }
            writer1.flush();
            writer1.close();
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(allProductsFileName));
            for (GroupOfItems group : groupsList){
                for (Items product : group.productsList){
                    writer2.write(group.getNameOfGroup() + ";" + product.getName() + ";" + product.getDescription() + ";" + product.getProducer() + ";" + product.getCount() + ";" + product.getPricePerOne());
                    writer2.newLine();
                }
            }
            writer2.flush();
            writer2.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Помилка при записі у файли.", "Помилка", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}