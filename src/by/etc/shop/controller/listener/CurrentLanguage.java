package by.etc.shop.controller.listener;

public enum CurrentLanguage {
    LANGUAGE;

    private static String language;

    static{language="ru";}

    CurrentLanguage() {}

    public static String getLanguage() {
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }
}
