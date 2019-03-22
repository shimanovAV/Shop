package by.etc.shop.entity;

import java.util.Iterator;
import java.util.List;


public class Basket {
    private static final int SHIFT = 31;
    private static final int START = 1;
    private static final double START_SUMM = 0.0;
    private int productId;
    private String userId;
    private int quantity;


    public Basket() {
    }

    public Basket(int productId, String userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Basket(int productId, String userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Basket basket = (Basket) o;
        return productId == basket.productId &&
                userId == basket.userId &&
                quantity == basket.quantity;
    }

    @Override
    public int hashCode() {
        final int prime = SHIFT;
        int result = START;
        result = prime * result + productId;
        result = prime * result + userId.hashCode();
        result = prime * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "productId=" + productId +
                ", userId=" + userId +
                ", quantity=" + quantity +
                '}';
    }

    public static double countSumm(List<Product> products) {
        double summ = START_SUMM;
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            summ += product.getPrice() * product.getQuantity();
        }
        return summ;
    }
}
