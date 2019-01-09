package by.etc.shop.dao.user;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.dao.hashing.Md5Encryption;
import by.etc.shop.entity.User;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    public static final String SELECT_ALL_WITH_LOGIN_PASSWORD = "select * from users where login = ? and password = ?";
    public static final String INSERT_ALL_FIELDS = "INSERT INTO users(login, name, password, email, birthday) VALUES(?,?,?,?,?)";
    public static final int LOGIN_COLUMN = 1;
    public static final int NAME_COLUMN = 2;
    public static final int PASSWORD_COLUMN = 3;
    public static final int EMAIL_COLUMN = 4;
    public static final int BIRTHDAY_COLUMN = 5;
    public static final int LOGIN_PARAMETR = 1;
    public static final int PASSWORD_PARAMETR = 2;
    public static final int ADDRESS_COLUMN = 6;
    public static final int NUMBER_CARD_COLUMN = 7;
    public static final int PHONE_NUMBER_COLUMN = 8;
    public static final int ACCESS_COLUMN = 9;
    private DataBaseHelper helper = new DataBaseHelper();

    @Override
    public boolean registration(User user, String password) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setString(LOGIN_COLUMN, user.getLogin());
            statement.setString(NAME_COLUMN, user.getUserName());
            String hashPassword = Md5Encryption.encrypt(password);
            statement.setString(PASSWORD_COLUMN, hashPassword);
            statement.setString(EMAIL_COLUMN, user.getEmail());
            java.sql.Date sqlDate = new java.sql.Date(user.getBirthday().getTime());
            statement.setDate(BIRTHDAY_COLUMN, sqlDate);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public User signIn(String login, String password) throws DAOException {
        PreparedStatement statement = null;
        User user = new User();
        ResultSet rs = null;
        try {
            helper.setQuery(SELECT_ALL_WITH_LOGIN_PASSWORD);
            statement = helper.getPreparedStatement();
            statement.setString(LOGIN_PARAMETR, login);
            String newPassword = Md5Encryption.encrypt(password);
            statement.setString(PASSWORD_PARAMETR, newPassword);
            rs = statement.executeQuery();
            if (rs.next()) {
                user.setLogin(rs.getString(LOGIN_COLUMN));
                user.setUserName(rs.getString(NAME_COLUMN));
                user.setEmail(rs.getString(EMAIL_COLUMN));
                user.setBirthday(rs.getDate(BIRTHDAY_COLUMN));
                user.setAddress(rs.getString(ADDRESS_COLUMN));
                user.setNumberOfCard(rs.getInt(NUMBER_CARD_COLUMN));
                user.setPhoneNumber(rs.getString(PHONE_NUMBER_COLUMN));
                user.setAccessLevel(rs.getBoolean(ACCESS_COLUMN));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
            return user;
        }

    }
}




