package by.etc.shop.service.stock;

import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;

import java.util.List;

public interface StockService {

    boolean add(Stock stock) throws ServiceException;
    boolean delete(int stockId) throws ServiceException;
    boolean update(Stock stock) throws ServiceException;
    double recalculation(String stockName, double oldPrice) throws ServiceException;
    List<Stock> getAll() throws ServiceException;

}
