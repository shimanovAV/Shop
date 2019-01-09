package by.etc.shop.service.product;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.product.ProductDAO;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    public boolean add(Product product) throws ServiceException{
        try{
            if (ProductValidator.isValid(product.getName())){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                ProductDAO productDAO = daoObjectFactory.getProductDAO();
                return productDAO.add(product);
            }
            else {
                throw new ServiceException("Name of the product is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean delete(Product product) throws ServiceException{
        try{
            if (ProductValidator.isValid(product.getName())){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                ProductDAO productDAO = daoObjectFactory.getProductDAO();
                return productDAO.delete(product);
            }
            else {
                throw new ServiceException("Name of the product is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean update(Product product) throws ServiceException{
        try{
            if (ProductValidator.isValid(product.getName())){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                ProductDAO productDAO = daoObjectFactory.getProductDAO();
                return productDAO.update(product);
            }
            else {
                throw new ServiceException("Name of the product is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    public List<Product> getAll() throws ServiceException{
        try {
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            ProductDAO productDAO = daoObjectFactory.getProductDAO();
            return productDAO.allProduct();
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

}
