package DB_file;

public class Clothes {
    /*
    Clothes to have the following attributes
    price
    quantity
     */

    private String name;
    private double price;
    private int quantity;

    public Clothes(String id){
        this.name = "";
        this.price = 0;
        this.quantity = 1;
    }

    public Clothes(String name, int quantity, double price){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

