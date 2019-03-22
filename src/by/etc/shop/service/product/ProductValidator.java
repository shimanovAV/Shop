package by.etc.shop.service.product;

public class ProductValidator {
    public static final int WRONG_PRODUCT_ID = 0;
    private static final String NULL = null;

    public static boolean isValid(int id) {
        return !(id <= WRONG_PRODUCT_ID);
    }

    public static boolean isValid(String login) {
        return !(login == NULL || login.isEmpty());
    }
}
