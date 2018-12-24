package by.etc.shop.entity;

import java.util.Date;
import java.util.Objects;

public class Product {
    private int id;
    private String name;
    private String description;
    private String category;
    private double price;
    private int quantity;
    private Date addingDate;
    private String stockName;
    private double oldPrice;
    private String path;


    public Product() {
    }

    public Product(String name, String description, String category, double price, int quantity, Date addingDate, String stockName, double oldPrice, String path) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.addingDate = addingDate;
        this.stockName = stockName;
        this.oldPrice = oldPrice;
        this.path = path;
    }

    public Product (String name, String description, String category, double price, int quantity, Date addingDate, String path) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.addingDate = addingDate;
        this.path = path;
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

    public String getCategory() {
        return category;
    }

    public String getStockId() {
        return stockName;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public void setStockId(String stockName) {
        this.stockName = stockName;
    }

    public void setCategory(String category) {
        this.category = category;
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
                Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                stockName.equals(product.stockName) &&
                Double.compare(product.oldPrice, oldPrice) == 0 &&
                name.equals(product.name) &&
                addingDate.equals(product.addingDate)&&
                description.equals(product.description) &&
                category.equals(product.category) &&
                path.equals(product.path);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + (int)price;
        result = prime * result + quantity;
        result = prime * result + stockName.hashCode();
        result = prime * result + (int)oldPrice;
        result = prime * result + name.hashCode();
        result = prime * result + description.hashCode();
        result = prime * result + category.hashCode();
        result = prime * result + path.hashCode();
        result = prime * result + addingDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", addingDate=" + addingDate +
                ", stockId=" + stockName +
                ", oldPrice=" + oldPrice +
                ", path='" + path + '\'' +
                '}';
    }
}
