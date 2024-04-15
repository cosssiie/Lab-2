import java.util.ArrayList;

public class GroupOfItems {
    /**Масив для зберігання товарів */
    public static ArrayList<Items> productsList = new ArrayList<>();
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

}

