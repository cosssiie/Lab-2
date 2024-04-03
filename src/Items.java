public class Items {

    //*Назва товару */
    private String name;

    //*Опис товару */
    private String description;

    //*Виробник товару */
    private String producer;

    //*Кількість товару на складі*/
    private int count;

    //*ціна за одиницю */
    private double pricePerOne;

    public Items(String name, String description, String producer, int count, double pricePerOne){
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.count = count;
        this.pricePerOne = pricePerOne;
    }

    // Getter methods
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProducer() {
        return producer;
    }

    public int getCount() {
        return count;
    }

    public double getPricePerOne() {
        return pricePerOne;
    }

    public String toString(){
        return "\nНазва товару: " + name +
        "\nОпис товару: " + description + 
        "\nВиробник: " + producer + 
        "\nКількість на складі: " + count + 
        "\nЦіна за одиницю: " + pricePerOne;
    }


}
