public class GroupOfItems {


     /**Назва групи товарів */
     public String nameOfGroup;

     /**Опис групи товарів */
     public String descriptionOfGroup;

     public GroupOfItems(String nameOfGroup, String descriptionOfGroup){
        this.nameOfGroup = nameOfGroup;
        this.descriptionOfGroup = descriptionOfGroup; 
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public String getDescriptionOfGroup() {
        return descriptionOfGroup;
    }

    public String toString(){
        return "\nНазва групи товарів: " + nameOfGroup +
        "\nОпис групи товарів: " + descriptionOfGroup;
    }

}

