package by.etc.shop.service.basket;

import by.etc.shop.entity.Basket;
import by.etc.shop.service.ServiceException;

public interface BasketService {
    boolean addToBasket(Basket basket) throws ServiceException;
    boolean deleteFromBasket(Basket basket) throws ServiceException;
    boolean changeQuantity(Basket basket) throws ServiceException;
}
