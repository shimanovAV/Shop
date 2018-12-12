package by.etc.shop.dao.user;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.User;

public interface UserDAO {
        boolean signIn(String login, String password) throws DAOException;
        boolean registration(User user) throws DAOException;
        boolean findByLogin(String login) throws DAOException;
    }


