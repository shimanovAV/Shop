package by.etc.shop.service.basket;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.basket.BasketDAO;
import by.etc.shop.entity.Basket;
import by.etc.shop.service.ServiceException;

public class BasketServiceImpl implements BasketService {

    public boolean addToBasket(Basket basket) throws ServiceException{
        try{
            if (BasketValidator.isValid(basket)){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                return basketDAO.add(basket);
            }
            else {
                throw new ServiceException("Basket is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean deleteFromBasket(Basket basket) throws ServiceException{
            try{
                if (BasketValidator.isValid(basket)){
                    DAOFactory daoObjectFactory = DAOFactory.getInstance();
                    BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                    return basketDAO.delete(basket);
                }
                else {
                    throw new ServiceException("Basket is not valid");
                }
            }catch(DAOException e){
                throw new ServiceException(e);
            }
    }
    public boolean changeQuantity(Basket basket) throws ServiceException{
        try{
            if (BasketValidator.isValid(basket)){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                BasketDAO basketDAO = daoObjectFactory.getBasketDAO();
                return basketDAO.update(basket);
            }
            else {
                throw new ServiceException("Basket is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

}
