package by.etc.shop.entity;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private Date orderDate;
    private double summ;
    private String userId;
    private boolean courier;

    public Order() {
    }

    public Order(int id, Date orderDate, double summ, String userId, boolean courier) {
        this.id = id;
        this.orderDate = orderDate;
        this.summ = summ;
        this.userId = userId;
        this.courier = courier;
    }

    public Order(Date orderDate, double summ, String userId, boolean courier) {
        this.orderDate = orderDate;
        this.summ = summ;
        this.userId = userId;
        this.courier = courier;
    }

    public int getId() {
        return id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public double getSumm() {
        return summ;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isCourier() {
        return courier;
    }

    public void setCourier(boolean courier) {
        this.courier = courier;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                Double.compare(order.summ, summ) == 0 &&
                userId.equals(order.userId) &&
                courier == order.courier &&
                orderDate.equals(order.orderDate);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + orderDate.hashCode();
        result = prime * result + (int)summ;
        result = prime * result + userId.hashCode();
        result = prime * result + ((Boolean)courier).compareTo(false);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", summ=" + summ +
                ", userId=" + userId +
                ", courier=" + courier +
                '}';
    }
    public static double countSumm(List<Product> products){
        double summ = 0.0;
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            summ+=product.getPrice()*product.getQuantity();
        }
        return summ;
    }
}
