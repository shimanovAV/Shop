package by.etc.shop.dao.like;

import by.etc.shop.dao.DAOException;
import by.etc.shop.entity.Like;
import by.etc.shop.entity.Product;

import java.util.List;

public interface LikeDAO {
    boolean add(Like like) throws DAOException;
    boolean delete(Like like) throws DAOException;
    List<Product> allProduct(String userLogin) throws DAOException;
}
