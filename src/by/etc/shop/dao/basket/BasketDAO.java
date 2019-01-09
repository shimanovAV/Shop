package by.etc.shop.dao.basket;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Product;

import java.util.List;

public interface BasketDAO {
    boolean add(Basket basket) throws DAOException;
    boolean delete(Basket basket) throws DAOException;
    List<Product> allProductInBasket(int userId) throws DAOException;
    boolean update(Basket basket) throws DAOException;
}
