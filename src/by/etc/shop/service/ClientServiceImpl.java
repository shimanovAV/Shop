package by.etc.shop.service;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.user.UserDAO;
import by.etc.shop.entity.User;

public class ClientServiceImpl implements ClientService {
    @Override
    public boolean singIn(String login, String password) throws ServiceException {
        try{
       if (ClientValidator.isValid(login,password)){
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            return userDAO.signIn(login, password);
        }
       else {
           throw new ServiceException("Enter login and password:");
       }
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(User user) throws ServiceException{
        try{
            if (ClientValidator.isValid(user.getUserName(),user.getLogin(),user.getPassword())) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                UserDAO userDAO = daoObjectFactory.getUserDAO();
                    return userDAO.registration(user);
            }
            else {
                throw new ServiceException("Enter your information:");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}

