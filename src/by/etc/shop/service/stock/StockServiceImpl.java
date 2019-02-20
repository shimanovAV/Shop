package by.etc.shop.service.stock;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.stock.StockDAO;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;

import java.util.List;

public class StockServiceImpl implements StockService {

    public static final int PERCENT = 100;

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
    public boolean delete(int stockId) throws ServiceException{
        try{
            if (stockId>0){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                StockDAO stockDAO = daoObjectFactory.getStockDAO();
                Stock stock = stockDAO.getStock(stockId);
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
            if (StockValidator.isValid(stock.getName())&& stock.getId()>0){
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

    public double recalculation(String stockName, double oldPrice) throws ServiceException{
        try{
            if (oldPrice>0.0 && StockValidator.isValid(stockName)){
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                StockDAO stockDAO = daoObjectFactory.getStockDAO();
                Stock stock = stockDAO.getStock(stockName);
                if(stock!=null){
                    return oldPrice*(PERCENT-stock.getPercentSize())/ PERCENT;
                }
                return oldPrice;
            }
            else {
                throw new ServiceException("Price or name of the product is not valid");
            }
        }catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    public double recalculation(double oldPrice, byte percentSize) throws ServiceException{

            if (oldPrice>0.0 && percentSize>=0 && percentSize<=100){

                    return oldPrice*(PERCENT-percentSize)/ PERCENT;
                }
            else {
                throw new ServiceException("Price or name of the product is not valid");
            }

    }

    public List<Stock> getAll() throws ServiceException{
       try{
            DAOFactory daoObjectFactory = DAOFactory.getInstance();
            StockDAO stockDAO = daoObjectFactory.getStockDAO();
            return stockDAO.getAll();
    }catch(DAOException e){
        throw new ServiceException(e);
    }
    }

}
