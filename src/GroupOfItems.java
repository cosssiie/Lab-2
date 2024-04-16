import java.util.ArrayList;

public class GroupOfItems {
    /**Масив для зберігання товарів */
    public ArrayList<Items> productsList = new ArrayList<>();
    /**Назва групи товарів */
    private String nameOfGroup;

    /**Опис групи товарів */
    private String groupDescription;

     public GroupOfItems(String nameOfGroup, String descriptionOfGroup){
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

    public void addProduct(String name, String desc, String producer, int count, int pricePerOne) throws IllegalArgumentException{
         if (productExists(name)){
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
         }
         productsList.add(new Items(name, desc, producer, count, pricePerOne));
    }

    public void editProduct(String oldName, String newName, String newDescription, String newProducer, String newCount, String newPricePerOne) throws IllegalArgumentException{
        if (!productExists(oldName)){
            throw new IllegalArgumentException("Товар з такою назвою не існує.");
        } else if (productExists(newName) && newDescription.trim().isEmpty() && newProducer.trim().isEmpty() && newCount.trim().isEmpty() && newPricePerOne.trim().isEmpty()){
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
        }
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

    public void removeProduct(String name) throws IllegalArgumentException{
        if (!productExists(name)){
            throw new IllegalArgumentException("Товар з такою назвою не існує.");
        }
        for (Items product : productsList){
            if (product.getName().equals(name)){
                productsList.remove(product);
                break;
            }
        }
    }

    public boolean productExists(String name){
        for (Items product : productsList){
            if (product.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Items getProductByName(String name){
        for (Items product : productsList){
            if (product.getName().equals(name)){
                return product;
            }
        }
        return null;
    }
}

