import java.util.ArrayList;

/**
 * Клас, що описує групу товарів
 */
public class GroupOfItems {
    private Main main;

    /**Масив для зберігання товарів */
    public ArrayList<Items> productsList = new ArrayList<>();
    /**Назва групи товарів */
    private String nameOfGroup;
    /**Опис групи товарів */
    private String groupDescription;
    /**
     * Конструктор класу
     * @param nameOfGroup - назва групи товарів
     * @param descriptionOfGroup - опис групи товарів
     * @param main - об'єкт класу Main
     */
    public GroupOfItems(String nameOfGroup, String descriptionOfGroup, Main main){
        this.main = main;
        this.nameOfGroup = nameOfGroup;
        this.groupDescription = descriptionOfGroup;
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String toString(){
        return "\nНазва групи товарів: " + nameOfGroup +
        "\nОпис групи товарів: " + groupDescription;
    }
    /**
     * Метод для додавання товару в групу
     * @param name - назва товару
     * @param desc - опис товару
     * @param producer - виробник товару
     * @param count - кількість товару на складі
     * @param pricePerOne - ціна за одиницю
     * @throws IllegalArgumentException
     */
    public void addProduct(String name, String desc, String producer, int count, int pricePerOne) throws IllegalArgumentException{
         if (main.productExists(name)){
             throw new IllegalArgumentException("Товар з такою назвою вже існує.");
         } else if (name.trim().isEmpty()){
             throw new IllegalArgumentException("Назва товару не може бути порожньою.");
         } else if (desc.trim().isEmpty()){
             throw new IllegalArgumentException("Опис товару не може бути порожнім.");
         } else if (producer.trim().isEmpty()){
             throw new IllegalArgumentException("Виробник товару не може бути порожнім.");
         } else if (count < 0){
             throw new IllegalArgumentException("Кількість товару не може бути від'ємною.");
         } else if (pricePerOne <= 0){
             throw new IllegalArgumentException("Ціна за одиницю товару повинна бути більше 0.");
         } else if (!name.matches("[a-zA-Zа-яА-ЯіІїЇєЄ0-9\\s]+")){
             throw new IllegalArgumentException("Назва товару повинна містити тільки літери та цифри.");
         } else if (name.length() > 33){
             throw new IllegalArgumentException("Назва товару повинна бути менше 33 символів.");
         }
        productsList.add(new Items(name, desc, producer, count, pricePerOne));
    }
    /**
     * Метод для редагування товару
     * @param oldName - стара назва товару
     * @param newName - нова назва товару
     * @param newDescription - новий опис товару
     * @param newProducer - новий виробник товару
     * @param newCount - нова кількість товару
     * @param newPricePerOne - нова ціна за одиницю товару
     * @throws IllegalArgumentException
     */
    public void editProduct(String oldName, String newName, String newDescription, String newProducer, String newCount, String newPricePerOne) throws IllegalArgumentException{
        if (!newCount.trim().isEmpty()) {
            try {
                Integer.parseInt(newCount);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Кількість товару повинна бути числом.");
            }
        }
        if (!newPricePerOne.trim().isEmpty()) {
            try {
                Integer.parseInt(newPricePerOne);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Ціна за одиницю товару повинна бути числом.");
            }
        }
        if (!main.productExists(oldName)){
            throw new IllegalArgumentException("Товар з такою назвою не існує.");
        } else if (main.productExists(newName) && newDescription.trim().isEmpty() && newProducer.trim().isEmpty() && newCount.trim().isEmpty() && newPricePerOne.trim().isEmpty()){
            throw new IllegalArgumentException("Товар має таку саму назву");
        } else if (newName.trim().isEmpty() && newDescription.trim().isEmpty() && newProducer.trim().isEmpty() && newCount.trim().isEmpty() && newPricePerOne.trim().isEmpty()){
            throw new IllegalArgumentException("Зміни не введено.");
        } else if (newName.trim().isEmpty() && !newDescription.trim().isEmpty() && getProductByName(oldName).getDescription().equals(newDescription) && newProducer.trim().isEmpty() && newCount.trim().isEmpty() && newPricePerOne.trim().isEmpty()){
            throw new IllegalArgumentException("Товар має такий самий опис.");
        } else if (newName.trim().isEmpty() && newDescription.trim().isEmpty() && !newProducer.trim().isEmpty() && getProductByName(oldName).getProducer().equals(newProducer) && newCount.trim().isEmpty() && newPricePerOne.trim().isEmpty()){
            throw new IllegalArgumentException("Товар має такого самого виробника.");
        } else if (newName.trim().isEmpty() && newDescription.trim().isEmpty() && newProducer.trim().isEmpty() && !newCount.trim().isEmpty() && getProductByName(oldName).getCount() == Integer.parseInt(newCount) && newPricePerOne.trim().isEmpty()){
            throw new IllegalArgumentException("Товар має таку саму кількість.");
        } else if (newName.trim().isEmpty() && newDescription.trim().isEmpty() && newProducer.trim().isEmpty() && newCount.trim().isEmpty() && !newPricePerOne.trim().isEmpty() && getProductByName(oldName).getPricePerOne() == Integer.parseInt(newPricePerOne)){
            throw new IllegalArgumentException("Товар має таку саму ціну за одиницю.");
        } else if (!newName.trim().isEmpty() && !newName.matches("[a-zA-Zа-яА-ЯіІїЇєЄ0-9\\s]+")){
            throw new IllegalArgumentException("Назва товару повинна містити тільки літери та цифри.");
        } else if (!newName.trim().isEmpty() && newName.length() > 33){
            throw new IllegalArgumentException("Назва товару повинна бути менше 33 символів.");
        } else
        if (!newCount.trim().isEmpty() && Integer.parseInt(newCount) < 0) {
            throw new IllegalArgumentException("Кількість товару не може бути від'ємною.");
        } else if (!newPricePerOne.trim().isEmpty() && Integer.parseInt(newPricePerOne) <= 0) {
            throw new IllegalArgumentException("Ціна за одиницю товару повинна бути більше 0.");
        }
        for (Items product : productsList) {
            if (product.getName().equals(oldName)) {
                if (!newName.trim().isEmpty()) {
                    product.setName(newName);
                }
                if (!newDescription.trim().isEmpty()) {
                    product.setDescription(newDescription);
                }
                if (!newProducer.trim().isEmpty()) {
                    product.setProducer(newProducer);
                }
                if (!newCount.trim().isEmpty()) {
                    product.setCount(Integer.parseInt(newCount));
                }
                if (!newPricePerOne.trim().isEmpty()) {
                    product.setPricePerOne(Integer.parseInt(newPricePerOne));
                }
                break;
            }
        }
    }
    /**
     * Метод для видалення товару
     * @param name - назва товару
     * @throws IllegalArgumentException
     */
    public void removeProduct(String name) throws IllegalArgumentException{
        if (!main.productExists(name)){
            throw new IllegalArgumentException("Товар з такою назвою не існує.");
        }
        for (Items product : productsList){
            if (product.getName().equals(name)){
                productsList.remove(product);
                break;
            }
        }
    }
    /**
     * Метод для отримання товару за назвою
     * @param name - назва товару
     * @return - об'єкт класу Items
     */
    public Items getProductByName(String name){
        for (Items product : productsList){
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
    /**
     * Метод для підрахунку загальної вартості товарів групи
     * @return - загальна вартість товарів групи
     */
    public int getTotalValue(){
        int totalValue = 0;
        for (Items product : productsList){
            totalValue += product.getCount() * product.getPricePerOne();
        }
        return totalValue;
    }
    /**
     * Метод для списання товару
     * @param name - назва товару
     * @param count - кількість товару для списання
     * @throws IllegalArgumentException
     */
    public void subtractProduct(String name, String count) throws IllegalArgumentException{
        if (!main.productExists(name)){
            throw new IllegalArgumentException("Товар з такою назвою не існує.");
        } else if (count == null || count.trim().isEmpty()){
            throw new IllegalArgumentException("Кількість товару не введено.");
        }
        int countInt;
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Кількість товару повинна бути числом.");
        }
        if (countInt <= 0){
            throw new IllegalArgumentException("Кількість товару повинна бути більше 0.");
        }
        Items product = getProductByName(name);
        if (product.getCount() - countInt < 0){
            throw new IllegalArgumentException("На складі недостатньо товару.");
        }
        product.setCount(product.getCount() - countInt);
    }
    /**
     * Метод для постачання товару
     * @param name - назва товару
     * @param count - кількість товару для постачання
     * @throws IllegalArgumentException
     */
    public void supplyProduct(String name, String count) throws IllegalArgumentException{
        if (!main.productExists(name)){
            throw new IllegalArgumentException("Товар з такою назвою не існує.");
        } else if (count == null || count.trim().isEmpty()){
            throw new IllegalArgumentException("Кількість товару не введено.");
        }
        int countInt;
        try {
            countInt = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Кількість товару повинна бути числом.");
        }
        if (countInt <= 0){
            throw new IllegalArgumentException("Кількість товару повинна бути більше 0.");
        }
        Items product = getProductByName(name);
        getProductByName(name).setCount(product.getCount() + countInt);
    }
}

