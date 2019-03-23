package by.etc.shop.entity;

import java.io.Serializable;
import java.util.Date;


public class Stock implements Serializable {
    private static final long serialVersionUID = 6259612578780114L;
    private static final int SHIFT = 31;
    private static final int START = 1;
    private int id;
    private String name;
    private byte percentSize;
    private Date expireDate;

    public Stock() {
    }

    public Stock(int id, String name, byte percentSize, Date expireDate) {
        this.id = id;
        this.name = name;
        this.percentSize = percentSize;
        this.expireDate = expireDate;
    }

    public Stock(String name, byte percentSize, Date expireDate) {
        this.name = name;
        this.percentSize = percentSize;
        this.expireDate = expireDate;
    }

    public String getName() {
        return name;
    }

    public byte getPercentSize() {
        return percentSize;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void setPercentSize(byte percentSize) {
        this.percentSize = percentSize;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id == stock.id &&
                percentSize == stock.percentSize &&
                name.equals(stock.name) &&
                expireDate.equals(stock.expireDate);
    }

    @Override
    public int hashCode() {
        final int prime = SHIFT;
        int result = START;
        result = prime * result + id;
        result = prime * result + name.hashCode();
        result = prime * result + (int) percentSize;
        result = prime * result + expireDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", percentSize=" + percentSize +
                ", expireDate=" + expireDate +
                ", id=" + id +
                '}';
    }
}
