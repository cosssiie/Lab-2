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

    /**
     * Точка входу в програму
     * @param args
     */
    public static void main(String[] args) {
        Main main = new Main("GroupsOfProducts.txt", "AllProducts.txt");
        main.start();
    }
    /**
     * Конструктор класу Main
     * @param groupsFileName - назва файлу для збереження груп товарів
     * @param allProductsFileName - назва файлу для збереження усіх товарів
     */
    public Main(String groupsFileName, String allProductsFileName) {
        this.groupsFileName = groupsFileName;
        this.allProductsFileName = allProductsFileName;
    }
    /**
     * Метод для запуску програми
     */
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
        statistics = new Statistics("Статистика", this, mainInterface);
        mainInterface.start();
    }
    /**
     * Метод для завантаження інформації з файлів
     */
    private void loadInformationFromFiles() {
        try(BufferedReader reader = new BufferedReader(new FileReader(groupsFileName))){
            String line;
            while ((line = reader.readLine()) != null){
                groupsList.add(new GroupOfItems(line.split(";")[0], line.split(";")[1], this));
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
    /**
     * Метод для отримання списку груп товарів
     * @return - список груп товарів
     */
    public ArrayList<GroupOfItems> getGroupsList() {
        return groupsList;
    }
    /**
     * Метод для отримання групи товарів за назвою
     * @param name - назва групи товарів
     * @return - група товарів
     */
    public GroupOfItems getGroupByName(String name){
        for (GroupOfItems group : groupsList){
            if (group.getNameOfGroup().equals(name)){
                return group;
            }
        }
        return null;
    }
    /**
     * Метод для перевірки існування групи товарів за назвою
     * @param name - назва групи товарів
     * @return - true, якщо група існує, false - якщо група не існує
     */
    public boolean groupExists(String name){
        for (GroupOfItems group : groupsList){
            if (group.getNameOfGroup().equals(name)){
                return true;
            }
        }
        return false;
    }
    /**
     * Метод для перевірки існування товару за назвою
     * @param name - назва товару
     * @return - true, якщо товар існує, false - якщо товар не існує
     */
    public boolean productExists(String name){
        for (GroupOfItems group : groupsList){
            for (Items product : group.productsList){
                if (product.getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * Метод для додавання товару
     * @param group - група товару
     * @throws IllegalArgumentException неправильні аргументи
     */
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

    /**
     * Метод для редагування групи товарів
     * @param oldName - стара назва групи
     * @param newName - нова назва групи
     * @param newDescription - новий опис групи
     * @throws IllegalArgumentException
     */
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
    /**
     * Метод для видалення групи товарів
     * @param name - назва групи
     * @throws IllegalArgumentException
     */
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
    /**
     * Метод для збереження інформації у файли
     */
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
    /**
     * Метод для отримання загальної вартості товарів на складі
     * @return - загальна вартість товарів на складі
     */
    public int getTotalStockValue(){
        int totalValue = 0;
        for (GroupOfItems group : groupsList){
            for (Items product : group.productsList){
                totalValue += product.getCount() * product.getPricePerOne();
            }
        }
        return totalValue;
    }
}