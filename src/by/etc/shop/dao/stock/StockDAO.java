package by.etc.shop.dao.stock;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;

import java.util.List;

public interface StockDAO {

    boolean add(Stock stock) throws DAOException;
    boolean delete(Stock stock) throws DAOException;
    List<Stock> allStock() throws DAOException;
    boolean update(Stock stock) throws DAOException;

}
