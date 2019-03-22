package by.etc.shop.service.order;

import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Product> makeOrder(Order order, List<Product> products) throws ServiceException;

    boolean cancelOrder(int orderId) throws ServiceException;

    boolean sendOrder(int orderId) throws ServiceException;

    Map<Order, List<Product>> allOrdersForOne(String userLogin) throws ServiceException;

    Map<Order, List<Product>> getAllFromOrder() throws ServiceException;
}
