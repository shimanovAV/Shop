package by.etc.shop.entity;

import java.io.Serializable;

public class Like implements Serializable {
    private static final long serialVersionUID = 625961578312780114L;

    private static final int SHIFT = 31;
    private static final int START = 1;
    private int productId;
    private String userId;


    public Like() {
    }

    public Like(int productId, String userId) {
        this.productId = productId;
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
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
        Like like = (Like) o;
        return productId == like.productId &&
                userId == like.userId;
    }

    @Override
    public int hashCode() {
        final int prime = SHIFT;
        int result = START;
        result = prime * result + productId;
        result = prime * result + userId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Like{" +
                "productId=" + productId +
                ", userId=" + userId +
                '}';
    }

}
