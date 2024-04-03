public class GroupOfItems {

     //*Назва групи товарів */
     private String nameOfGroup;

     //*Опис групи товарів */
     private String descriptionOfGroup;

     public GroupOfItems(String nameOfGroup, String descriptionOfGroup){
        this.nameOfGroup = nameOfGroup;
        this.descriptionOfGroup = descriptionOfGroup; 
    }

    public String toString(){
        return "\nНазва групи товарів: " + nameOfGroup +
        "\nОпис групи товарів: " + descriptionOfGroup;
    }
    

}

