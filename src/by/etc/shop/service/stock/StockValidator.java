package by.etc.shop.service.stock;

public class StockValidator {

    public static boolean isValid(String name){
        return !(name == null || name.isEmpty());
    }

    public static boolean isValid(int id){
        return !(id<=0);
    }

    public static boolean isValid(double price){
        return !(price<=0.0);
    }

    public static boolean isValid(byte percentSize){
        return !(percentSize<0 || percentSize>100);
    }

}
