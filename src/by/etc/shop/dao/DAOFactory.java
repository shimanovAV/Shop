package by.etc.shop.dao;

import by.etc.shop.dao.basket.BasketDAO;
import by.etc.shop.dao.basket.SQLBasketDAO;
import by.etc.shop.dao.like.LikeDAO;
import by.etc.shop.dao.like.SQLLikeDAO;
import by.etc.shop.dao.order.OrderDAO;
import by.etc.shop.dao.order.SQLOrderDAO;
import by.etc.shop.dao.product.ProductDAO;
import by.etc.shop.dao.product.SQLProductDAO;
import by.etc.shop.dao.stock.SQLStockDAO;
import by.etc.shop.dao.stock.StockDAO;
import by.etc.shop.dao.user.SQLUserDAO;
import by.etc.shop.dao.user.UserDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUserImpl = new SQLUserDAO();
    private final ProductDAO sqlProductImpl = new SQLProductDAO();
    private final StockDAO sqlStockImpl = new SQLStockDAO();
    private final BasketDAO sqlBasketImpl = new SQLBasketDAO();
    private final LikeDAO sqlLikeImpl = new SQLLikeDAO();
    private final OrderDAO sqlOrderImpl = new SQLOrderDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO(){
        return sqlUserImpl;
    }

    public ProductDAO getProductDAO(){
        return sqlProductImpl;
    }

    public StockDAO getStockDAO(){
        return sqlStockImpl;
    }

    public BasketDAO getBasketDAO(){
        return sqlBasketImpl;
    }

    public LikeDAO getLikeDAO() { return sqlLikeImpl; }

    public OrderDAO getOrderDAO() { return sqlOrderImpl; }
}
