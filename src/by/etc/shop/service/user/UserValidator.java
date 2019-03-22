package by.etc.shop.service.user;

public class UserValidator {

    public static final String CORRECT_EMAIL = "^[\\w._\\d-]+@[A-Za-z]+.[A-Za-z]{2,3}$";

    public static boolean isValid(String login, String password) {
        return !(login == null || login.isEmpty() ||
                password == null || password.isEmpty());
    }

    public static boolean isValid(String name, String login, String password, String email) {
        return !(!isValid(login, password) || name == null || name.isEmpty()
                || !email.matches(CORRECT_EMAIL));
    }
}
