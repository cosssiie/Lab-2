public class Items {

    /**Назва товару */
    public static String name;

    /**Опис товару */
    public static String description;

    /**Виробник товару */
    public static String producer;

    /**Кількість товару на складі*/
    public static int count;

    /**Ціна за одиницю */
    public static int pricePerOne;

    public Items(String name, String description, String producer, int count, int pricePerOne){
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
