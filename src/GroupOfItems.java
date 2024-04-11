public class GroupOfItems {


     /**Назва групи товарів */
     public String nameOfGroup;

     /**Опис групи товарів */
     public String groupDescription;

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

    public String toString(){
        return "\nНазва групи товарів: " + nameOfGroup +
        "\nОпис групи товарів: " + groupDescription;
    }

}

