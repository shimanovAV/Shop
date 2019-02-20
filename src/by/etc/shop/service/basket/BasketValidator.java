package by.etc.shop.service.basket;

import by.etc.shop.entity.Basket;

public class BasketValidator {

    public static boolean isValid(Basket basket){
        if(basket.getProductId()<=0 ||
        basket.getUserId()==null || basket.getUserId().isEmpty()|| basket.getQuantity()<=0){
            return false;
        }
        return true;
    }
}
