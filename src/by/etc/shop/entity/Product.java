package by.etc.shop.entity;

public class Product {
    private int id;
    private String path;
    private String name;
    private String description;
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(int id, String path, String name, String description, double price, int quantity) {
        this.id = id;
        this.path = path;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                path.equals(product.path) &&
                Double.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                quantity == product.quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + path.hashCode();
        result = prime * result + name.hashCode();
        result = prime * result + description.hashCode();
        result = prime * result + (int)price;
        result = prime * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
