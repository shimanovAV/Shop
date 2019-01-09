package by.etc.shop.service.stock;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.stock.StockDAO;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;

public class StockServiceImpl implements StockService {

    public boolean add(Stock stock) throws ServiceException {
        try{
            if (StockValidator.isValid(stock.getName())){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                StockDAO stockDAO = daoObjectFactory.getStockDAO();
                return stockDAO.add(stock);
            }
            else {
                throw new ServiceException("Name of the stock is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean delete(Stock stock) throws ServiceException{
        try{
            if (StockValidator.isValid(stock.getName())){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                StockDAO stockDAO = daoObjectFactory.getStockDAO();
                return stockDAO.delete(stock);
            }
            else {
                throw new ServiceException("Name of the stock is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    public boolean update(Stock stock) throws ServiceException{
        try{
            if (StockValidator.isValid(stock.getName())){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                StockDAO stockDAO = daoObjectFactory.getStockDAO();
                return stockDAO.update(stock);
            }
            else {
                throw new ServiceException("Name of the stock is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }


}
