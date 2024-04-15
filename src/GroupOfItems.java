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

    public void addProduct(Items product) throws IllegalArgumentException{
         if (productExists(product.getName())){
             throw new IllegalArgumentException("Товар з такою назвою вже існує.");
         }
         productsList.add(product);
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
}

