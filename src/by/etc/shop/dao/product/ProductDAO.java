package by.etc.shop.dao.product;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Product;

import java.util.List;

public interface ProductDAO {
    boolean add(Product product) throws DAOException;
    boolean delete(Product product) throws DAOException;
    List<Product> allBook() throws DAOException;
    boolean update(Product product, int id) throws DAOException;
}
