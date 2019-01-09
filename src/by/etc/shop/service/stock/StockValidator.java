package by.etc.shop.service.stock;

public class StockValidator {

    public static boolean isValid(String name){
        if(name == null || name.isEmpty()){
            return false;
        }
        return true;
    }

}
