package by.etc.shop.service.basket;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.basket.BasketDAO;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;

public class BasketServiceImpl implements BasketService {

    public boolean addToBasket(Basket basket) throws ServiceException {
        try {
            if (BasketValidator.isValid(basket)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                return basketDAO.add(basket);
            } else {
                throw new ServiceException("Basket is not valid");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean deleteFromBasket(Basket basket) throws ServiceException {
        try {
            if (BasketValidator.isValid(basket)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                return basketDAO.delete(basket);
            } else {
                throw new ServiceException("Basket is not valid");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public boolean changeQuantity(Basket basket) throws ServiceException {
        try {
            if (BasketValidator.isValid(basket)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                return basketDAO.update(basket);
            } else {
                throw new ServiceException("Basket is not valid");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Product> getAllFromBasket(String userLogin) throws ServiceException {
        try {
            if (BasketValidator.isValidLogin(userLogin)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                return basketDAO.allProduct(userLogin);
            } else {
                throw new ServiceException("Basket is not valid");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
