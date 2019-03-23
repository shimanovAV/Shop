package by.etc.shop.service.order;

import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;

import java.util.List;

public class OrderValidator {

    private static final String NULL = null;
    private static final int ZERO = 0;
    private static final Order ORDER_NULL = null;
    private static final List<Product> LIST_NULL = null;

    public static boolean isValid(Order order, List<Product> products) {
       return !(order == ORDER_NULL || products == LIST_NULL);
    }

    public static boolean isValid(Order order) {
       return !(order == ORDER_NULL);
    }

    public static boolean isValid(int orderId) {
        return !(orderId <= ZERO);
    }

    public static boolean isValid(List<Product> products) {
        return !(products == LIST_NULL || products.size() == ZERO);
    }

    public static boolean isValid(String login) {
       return !(login == NULL || login.isEmpty());
    }
    public static boolean isValidQuantity(int diffQuantity) {
        return diffQuantity<0;
    }

}
