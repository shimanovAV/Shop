package by.etc.shop.dao.order;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;

import java.util.List;
import java.util.Map;

public interface OrderDAO {

    boolean add(Order order, List<Product> products) throws DAOException;
    boolean delete(int orderId) throws DAOException;
    boolean send(int orderId) throws DAOException;
    List<Product> allProductBy(int orderId) throws DAOException;
    Map<Order, List<Product>> allProduct() throws DAOException;
    Map<Order, List<Product>> allOrdersForOne(String userLogin) throws DAOException;

}
