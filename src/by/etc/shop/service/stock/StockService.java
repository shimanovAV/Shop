package by.etc.shop.service.stock;

import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;

public interface StockService {

    boolean add(Stock stock) throws ServiceException;
    boolean delete(Stock stock) throws ServiceException;
    boolean update(Stock stock) throws ServiceException;

}
