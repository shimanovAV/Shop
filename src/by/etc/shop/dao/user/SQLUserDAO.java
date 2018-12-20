package by.etc.shop.dao.user;
import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.dao.hashing.Md5Encryption;
import by.etc.shop.entity.User;

import java.sql.*;

public class SQLUserDAO implements UserDAO {
    public static final String SELECT_ALL_WITH_LOGIN_PASSWORD= "select * from users where login = ? and password = ?";
    public static final String INSERT_ALL_FIELDS = "INSERT INTO users(username, login, password, email ) VALUES(?,?,?,?)";
    private DataBaseHelper helper = new DataBaseHelper();
    @Override
    public boolean registration(User user) throws DAOException{
        PreparedStatement statement = null;
        try {
                helper.setQuery(INSERT_ALL_FIELDS);
                statement = helper.getPreparedStatement();
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getLogin());
          user.setPassword(Md5Encryption.encrypt(user.getPassword()));
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public boolean signIn(String login, String password) throws DAOException {
        PreparedStatement statement=null;
        User user = null;
        ResultSet rs = null;
        try{
        helper.setQuery(SELECT_ALL_WITH_LOGIN_PASSWORD);
        statement = helper.getPreparedStatement();
        statement.setString(1,login);
       String newPassword=Md5Encryption.encrypt(password);
       statement.setString(2,newPassword);
                rs = statement.executeQuery();
                return rs.next();
            } catch (SQLException e) {
                throw new DAOException(e);
            }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
        }

    }
    }




