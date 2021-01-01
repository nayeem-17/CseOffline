class Product implements Comparable<Product> {

    private String name;
    private double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return "The name of the Product is " + this.name + ". the price of the product is  " + this.price;
    }

    @Override
    public int compareTo(Product arg0) {
        return Double.compare(this.price, arg0.price);
    }
}