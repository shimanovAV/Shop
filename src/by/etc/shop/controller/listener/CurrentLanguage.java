package by.etc.shop.controller.listener;

public enum CurrentLanguage {
    LANGUAGE;

    private static String language;

    public static final String START_LANGUAGE = "ru";

    static{language= START_LANGUAGE;}

    CurrentLanguage() {}

    public static String getLanguage() {
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }
}
