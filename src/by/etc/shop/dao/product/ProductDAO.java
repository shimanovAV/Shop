package by.etc.shop.dao.product;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Product;

import java.util.List;

public interface ProductDAO {
    boolean add(Product product) throws DAOException;
    boolean delete(int productID) throws DAOException;
    List<Product> allProduct() throws DAOException;
    boolean update(Product product) throws DAOException;
    Product getProductById(int productID) throws DAOException;
}
