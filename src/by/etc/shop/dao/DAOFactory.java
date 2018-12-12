package by.etc.shop.dao;

import by.etc.shop.dao.user.SQLUserDAO;
import by.etc.shop.dao.user.UserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUserImpl = new SQLUserDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO(){
        return sqlUserImpl;
    }
}
