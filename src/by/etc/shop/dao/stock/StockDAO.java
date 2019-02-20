package by.etc.shop.dao.stock;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;

import java.util.List;

public interface StockDAO {

    boolean add(Stock stock) throws DAOException;
    boolean delete(Stock stock) throws DAOException;
    boolean update(Stock stock) throws DAOException;
    Stock getStock(String stockName) throws DAOException;
    Stock getStock(int stockId) throws DAOException;
    List <Stock> getAll() throws DAOException;

}
