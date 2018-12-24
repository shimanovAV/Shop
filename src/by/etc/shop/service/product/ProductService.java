package by.etc.shop.service.product;

import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

public interface ProductService {
    boolean add(Product product) throws ServiceException;
    boolean delete(Product product) throws ServiceException;
    boolean update(Product product, int id) throws ServiceException;
}
