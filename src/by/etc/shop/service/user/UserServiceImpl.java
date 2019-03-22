package by.etc.shop.service.user;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.user.UserDAO;
import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;

public class UserServiceImpl implements UserService {
    @Override
    public User singIn(String login, String password) throws ServiceException {
        try {
            if (UserValidator.isValid(login, password)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                UserDAO userDAO = daoObjectFactory.getUserDAO();
                return userDAO.signIn(login, password);
            } else {
                return null;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(User user, String password) throws ServiceException {
        try {
            if (UserValidator.isValid(user.getUserName(), user.getLogin(),
                    password, user.getEmail())) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                UserDAO userDAO = daoObjectFactory.getUserDAO();
                return userDAO.registration(user, password);
            } else {
                return false;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

