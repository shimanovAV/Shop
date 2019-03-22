package by.etc.shop.service.user;

import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;

public interface UserService {
    User singIn(String login, String password) throws ServiceException;

    boolean registration(User user, String password) throws ServiceException;
}
