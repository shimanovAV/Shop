package by.etc.shop.service.product;

import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;

public interface ProductService {
    boolean add(Product product) throws ServiceException;
    boolean delete(int productID) throws ServiceException;
    boolean update(Product product) throws ServiceException;
    Product getProductById(int productID) throws ServiceException;
    List<Product> getAll() throws ServiceException;

}
