package by.etc.shop.test.dao;
import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.user.UserDAO;
import by.etc.shop.entity.User;
import org.testng.Assert;

import org.testng.annotations.Test;

public class UserDAOTest extends Assert {


    private DAOFactory daoObjectFactory = DAOFactory.getInstance();
    private UserDAO userDAO = daoObjectFactory.getUserDAO();

    @Test

    public void signInTestPositive() throws DAOException {

        String login = "admin";
        String password = "admin";

        User user = userDAO.signIn(login, password);
        assertNotNull(user);
    }

    @Test

    public void signInTestNegative() throws DAOException {

        String login = "fgh";
        String password = "fghj";
        User user = userDAO.signIn(login, password);
        assertNull(user);

    }

}