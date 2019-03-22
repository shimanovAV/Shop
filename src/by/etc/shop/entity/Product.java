package by.etc.shop.entity;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Product {
    private static final String NO = "no";
    private static final String BOYS = "Boys";
    private static final int SHIFT = 31;
    private static final int START = 1;
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

    public Product(int id, String name, String description, String category, double price, int quantity, Date addingDate, String stockName, double oldPrice, String path) {
        this.id = id;
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

    public Product(int id, String name, String description, String category, double price, int quantity, Date addingDate, String path) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.addingDate = addingDate;
        this.path = path;
    }

    public Product(String name, String description, String category, double price, int quantity, Date addingDate, String path) {
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

    public String getStockName() {
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

    public boolean isNew() {
        Date today = new Date();
        long diff = today.getTime() - this.addingDate.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return days <= 3;
    }

    public boolean isOnSale() {
        return !(this.stockName == null || this.stockName.equals(NO));
    }

    public boolean forBoy() {
        return this.category.equals(BOYS);
    }

    public boolean hasLike(List<Product> likes) {
        if (likes != null) {
            return likes.contains(this);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                name.equals(product.name) &&
                addingDate.equals(product.addingDate) &&
                description.equals(product.description) &&
                category.equals(product.category) &&
                path.equals(product.path);
    }

    @Override
    public int hashCode() {
        final int prime = SHIFT;
        int result = START;
        result = prime * result + id;
        result = prime * result + (int) price;
        result = prime * result + quantity;
        result = prime * result + stockName.hashCode();
        result = prime * result + (int) oldPrice;
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
