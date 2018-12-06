package by.etc.shop.service;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.UserDAO;
import by.etc.shop.entity.User;

public class ClientServiceImpl implements ClientService {
    @Override
    public boolean singIn(String login, String password) throws ServiceException {
        boolean flag = false;
        if(login == null || login.isEmpty()){
            throw new ServiceException("Enter login");
        }
        if(password == null || password.isEmpty()){
            throw new ServiceException("Enter password");
        }
        try{
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            flag = userDAO.signIn(login, password);
        }catch(DAOException e){
            throw new ServiceException(e);
        }
        return flag;
    }

    @Override
    public boolean registration(User user) throws ServiceException{
        boolean flag = false;
        if(user.getUserName() == null || user.getUserName().isEmpty()){
            throw new ServiceException("Enter name");
        }
        if(user.getLogin() == null || user.getLogin().isEmpty()){
            throw new ServiceException("Enter login");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new ServiceException("Enter password");
        }
        try{
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoObjectFactory.getUserDAO();
            if(!userDAO.findByLogin(user.getLogin())){
                flag = userDAO.registration(user);
            } else {
                throw new ServiceException("User with this login is already exists");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
        return flag;
    }
}

