package by.etc.shop.service;

import by.etc.shop.entity.User;

public interface ClientService {
    boolean singIn(String login, String password) throws ServiceException;
    boolean registration(User user) throws ServiceException;
}
