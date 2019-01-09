package by.etc.shop.dao.stock;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.entity.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLStockDAO implements StockDAO {

    public static final String INSERT_ALL_FIELDS = "INSERT INTO stock(name, percentSize" +
            "expireDate) VALUES(?,?,?)";
    public static final String DELETE_ALL_ID= "delete from stock where id = ?";
    public static final String SELECT_ALL= "select * from stock";
    public static final String UPDATE_WHERE_ID= "UPDATE stock SET name=?," +
            "percentSize = ?, expireDate = ? WHERE id = ?";
    private DataBaseHelper helper = new DataBaseHelper();
    @Override
    public boolean add(Stock stock) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setString(1, stock.getName());
            statement.setByte(2,stock.getPercentSize());
            java.sql.Date sqlDate = new java.sql.Date(stock.getExpireDate().getTime());
            statement.setDate(3,sqlDate);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public boolean delete(Stock stock) throws DAOException{
        PreparedStatement statement=null;
        try {
            helper.setQuery(DELETE_ALL_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, stock.getId());
            statement.executeUpdate();
            return true;
        } catch(SQLException e){
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public List<Stock> allStock() throws DAOException{
        PreparedStatement statement=null;
        List<Stock> stocks = new ArrayList<>();
        ResultSet rs = null;
        String query = SELECT_ALL;
        try{
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
            rs = statement.executeQuery();
            while(rs.next()){
                Stock stock = null;
                int id = rs.getInt(1);
                String name = rs.getString(2);
                byte percentSize = rs.getByte(3);
                Date expireDate = rs.getDate(4);
                stock = new Stock(id, name, percentSize, expireDate);
                stocks.add(stock);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
            return stocks;
        }
    }
    public boolean update(Stock stock) throws DAOException{
        PreparedStatement statement = null;
        try {
            helper.setQuery(UPDATE_WHERE_ID);
            statement = helper.getPreparedStatement();
            statement.setString(1, stock.getName());
            statement.setByte(2, stock.getPercentSize());
            java.sql.Date sqlDate = new java.sql.Date(stock.getExpireDate().getTime());
            statement.setDate(3,sqlDate);
            statement.setInt(4, stock.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

}
