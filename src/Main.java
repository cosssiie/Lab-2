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
    public final String allProducts; //файл для збереження груп товарів

    public static void main(String[] args) {
        Main main = new Main("GroupsOfProducts.txt", "AllProducts.txt");
        main.start();
    }

    public Main(String groupsFileName, String allProducts) {
        this.groupsFileName = groupsFileName;
        this.allProducts = allProducts;
    }

    private void start() {
        mainInterface = new Interface("Stock", this);
        productSearch = new ProductSearch("Пошук товару");
        editProducts = new EditProducts("Додавання товару", this, mainInterface);
        //supplyOfProducts = new SupplyOfProducts("Поставка товару");
        //statistics = new Statistics("Статистика");
        mainInterface.start();
    }
}