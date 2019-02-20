package by.etc.shop.entity;

public class Like {

    private int productId;
    private String userId;



    public Like() {
    }

    public Like(int productId, String userId, int quantity) {
        this.productId = productId;
        this.userId = userId;
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
        final int prime = 31;
        int result = 1;
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
