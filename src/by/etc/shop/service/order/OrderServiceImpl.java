package by.etc.shop.service.order;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.order.OrderDAO;
import by.etc.shop.dao.product.ProductDAO;
import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.product.ProductValidator;
import by.etc.shop.service.user.UserValidator;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    public List<Product> makeOrder(Order order, List<Product> products) throws ServiceException {
        try{
            List<Product> notAccepted = new LinkedList<>();
            if (OrderValidator.isValid(order, products)){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                ProductDAO productDAO = daoObjectFactory.getProductDAO();
                Iterator<Product> iterator = products.listIterator();
                while(iterator.hasNext()){
                    Product product = iterator.next();
                    if(productDAO.getQuantity(product.getId(), product.getQuantity())<0){
                        iterator.remove();
                        notAccepted.add(product);
                    }
                }
                if(products!=null && products.size()!=0){
                    double newSumm = Order.countSumm(products);
                    order.setSumm(newSumm);
                    OrderDAO orderDAO = daoObjectFactory.getOrderDAO();
                    orderDAO.add(order, products);
                }
                return notAccepted;
            }
            else {
                throw new ServiceException("Order is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    public boolean cancelOrder(int orderId) throws ServiceException{
        try{
            if (OrderValidator.isValid(orderId)){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                OrderDAO orderDAO = daoObjectFactory.getOrderDAO();
                return orderDAO.delete(orderId);
            }
            else {
                throw new ServiceException("Order is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    public Map<Order, List<Product>> allOrdersForOne(String userLogin) throws ServiceException{
        try {
            if(userLogin!=null || !(userLogin.isEmpty())) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                OrderDAO orderDAO = daoObjectFactory.getOrderDAO();
                return orderDAO.allOrdersForOne(userLogin);
            }
            else{
                throw new ServiceException("Basket is not valid");
            }
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    public Map<Order, List<Product>> getAllFromOrder() throws ServiceException{
        try {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                OrderDAO orderDAO = daoObjectFactory.getOrderDAO();
                return orderDAO.allProduct();
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean sendOrder(int orderId) throws ServiceException{
        try{
            if (OrderValidator.isValid(orderId)){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                ProductDAO productDAO = daoObjectFactory.getProductDAO();
                OrderDAO orderDAO = daoObjectFactory.getOrderDAO();
                return orderDAO.send(orderId);
            }
            else {
                throw new ServiceException("Order is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
}