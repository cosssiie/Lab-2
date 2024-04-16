public class Items {
    /**Назва товару */
    private String name;

    /**Опис товару */
    private String description;

    /**Виробник товару */
    private String producer;

    /**Кількість товару на складі*/
    private int count;

    /**Ціна за одиницю */
    private int pricePerOne;

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

    public int getPricePerOne() {
        return pricePerOne;
    }

    public void setPricePerOne(int pricePerOne) {
        this.pricePerOne = pricePerOne;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "\nНазва товару: " + name +
        "\nОпис товару: " + description + 
        "\nВиробник: " + producer + 
        "\nКількість на складі: " + count + 
        "\nЦіна за одиницю: " + pricePerOne;
    }


}
