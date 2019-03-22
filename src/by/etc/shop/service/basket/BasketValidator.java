package by.etc.shop.service.basket;

import by.etc.shop.entity.Basket;

public class BasketValidator {

    private static final int ZERO = 0;
    private static final String NULL = null;

    public static boolean isValid(Basket basket) {
       return !(basket.getProductId() <= ZERO ||
                basket.getUserId() == NULL || basket.getUserId().isEmpty() || basket.getQuantity() <= ZERO);
    }

    public static boolean isValidLogin(String login) {
        return !(login == NULL || login.isEmpty());
    }

}
