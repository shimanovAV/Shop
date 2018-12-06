package by.etc.shop.dao;
import by.etc.shop.entity.User;

import java.sql.*;

public class SQLUserDAO implements UserDAO{
    public static final String SELECT_ALL = "select * from users";
    public static final String INCERT_ALL_FIELDS = "INSERT INTO users(username, login, password, email ) VALUES(?,?,?,?)";
    DataBaseHelper helper = null;
    PreparedStatement statement = null;
    @Override
    public boolean signIn(String login, String password) throws DAOException {
        boolean flag = false;
            try {
                helper = new DataBaseHelper(SELECT_ALL);
                statement = helper.getPreparedStatement();
                User user = helper.getUser(statement, login, password);
                flag = true;
                if(user==null) {
                    flag = false;
                }
            } finally {
                helper.closeStatement(statement);
            }
            return flag;
}

    @Override
    public boolean registration(User user) throws DAOException{
        boolean flag = false;
        try {
            helper = new DataBaseHelper(INCERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            flag =  helper.insertUser(statement, user);
            if(!flag){
                throw new DAOException("User wasn't added");
            }
            }
        finally {
            helper.closeStatement(statement);
        }
        return flag;
    }

    public boolean findByLogin(String login) throws DAOException{
        boolean flag = false;
        try {
            helper = new DataBaseHelper(SELECT_ALL);
            statement = helper.getPreparedStatement();
            flag = helper.hasUser(statement, login);
        } finally {
            helper.closeStatement(statement);
        }
        return flag;
    }

    }




