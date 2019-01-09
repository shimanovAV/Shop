package by.etc.shop.service.product;

import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;

public interface ProductService {
    boolean add(Product product) throws ServiceException;
    boolean delete(Product product) throws ServiceException;
    boolean update(Product product) throws ServiceException;
    List<Product> getAll() throws ServiceException;
}
