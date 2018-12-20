package by.etc.shop.service;

public class ClientValidator {
    public static boolean isValid(String login, String password){
        if(login == null || login.isEmpty() ||
                password == null || password.isEmpty()){
            return false;
        }
       return true;
    }
    public static boolean isValid(String name, String login, String password){
        if(!isValid(login,password)|| name == null || name.isEmpty()){
                return false;
            }
        return true;
        }
    }
