package by.etc.shop.service.order;

import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;

import java.util.List;

public class OrderValidator {
    public static boolean isValid(Order order, List<Product> products){
        if(order == null || products == null){
            return false;
        }
        return true;
    }
    public static boolean isValid(Order order){
        if(order == null){
            return false;
        }
        return true;
    }
    public static boolean isValid(int orderId){
        if(orderId <= 0){
            return false;
        }
        return true;
    }
}
