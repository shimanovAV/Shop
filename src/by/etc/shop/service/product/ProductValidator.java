package by.etc.shop.service.product;

public class ProductValidator {
    public static boolean isValid(String name){
        if(name == null || name.isEmpty()){
            return false;
        }
        return true;
    }
}
