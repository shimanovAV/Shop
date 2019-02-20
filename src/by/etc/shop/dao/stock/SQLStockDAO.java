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

    public static final String INSERT_ALL_FIELDS = "INSERT INTO stock(name, percentSize, " +
            "expireDate) VALUES(?,?,?)";
    public static final String DELETE_ALL_ID = "delete from stock where id = ?";
    public static final String DELETE_STOCK_IN_PRODUCT = "update product set stock_name=null, " +
            "price=old_price where stock_name = ?";
    public static final String SELECT_ALL = "select * from stock";
    public static final String SELECT_BY_NAME = "select * from stock where name = ?";
    public static final String SELECT_BY_ID = "select * from stock where id = ?";
    public static final String UPDATE_WHERE_ID = "UPDATE stock SET name=?," +
            "percentSize = ?, expireDate = ? WHERE id = ?";
    public static final String UPDATE_STOCK_IN_PRODUCT = "UPDATE product SET stock_name=?," +
            "price=(old_price*(100 - percentSize=?)/100) WHERE stock_name = ?";


    private DataBaseHelper helper = new DataBaseHelper();

    @Override
    public boolean add(Stock stock) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setString(1, stock.getName());
            statement.setByte(2, stock.getPercentSize());
            java.sql.Date sqlDate = new java.sql.Date(stock.getExpireDate().getTime());
            statement.setDate(3, sqlDate);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public boolean delete(Stock stock) throws DAOException {
        PreparedStatement statementStockDB = null;
        PreparedStatement statementProductDB = null;
        try {
            helper.setQuery(DELETE_ALL_ID);
            statementStockDB = helper.getPreparedStatementManualCon();
            helper.setQuery(DELETE_STOCK_IN_PRODUCT);
            statementProductDB = helper.getPreparedStatementManualCon();
            statementStockDB.setInt(1, stock.getId());
            statementProductDB.setString(1, stock.getName());
            statementStockDB.executeUpdate();
            statementProductDB.executeUpdate();
            helper.commit(statementStockDB);
            helper.commit(statementProductDB);
            return true;
        } catch (SQLException e) {
            helper.rollBack(statementStockDB);
            helper.rollBack(statementProductDB);
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statementStockDB);
            helper.closeStatement(statementProductDB);
            helper.returnConnection();
        }
    }

    public List<Stock> getAll() throws DAOException {
        PreparedStatement statement = null;
        List<Stock> stocks = new ArrayList<>();
        ResultSet rs = null;
        String query = SELECT_ALL;
        try {
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
            rs = statement.executeQuery();
            while (rs.next()) {
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
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
            return stocks;
        }
    }

    public boolean update(Stock stock) throws DAOException {
        PreparedStatement statementStockDB = null;
        PreparedStatement statementProductDB = null;
        ResultSet rs = null;
        try {
            helper.setQuery(SELECT_BY_ID);
            statementStockDB = helper.getPreparedStatementManualCon();
            statementStockDB.setInt(1, stock.getId());
            rs = statementStockDB.executeQuery();
            if (rs.next()) {
                String oldStockName = rs.getString(2);
                helper.setQuery(UPDATE_STOCK_IN_PRODUCT);
                statementProductDB = helper.getPreparedStatementManualCon();
                statementProductDB.setString(1, stock.getName());
                statementProductDB.setByte(2, stock.getPercentSize());
                statementProductDB.setString(3, oldStockName);
                helper.setQuery(UPDATE_WHERE_ID);
                statementStockDB = helper.getPreparedStatementManualCon();
                statementStockDB.setString(1, stock.getName());
                statementStockDB.setByte(2, stock.getPercentSize());
                java.sql.Date sqlDate = new java.sql.Date(stock.getExpireDate().getTime());
                statementStockDB.setDate(3, sqlDate);
                statementStockDB.setInt(4, stock.getId());
                statementStockDB.executeUpdate();
                helper.commit(statementStockDB);
                helper.commit(statementProductDB);
                return true;
            }
            return false;
        } catch (SQLException e) {
            helper.rollBack(statementStockDB);
            helper.rollBack(statementProductDB);
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statementStockDB);
            helper.closeStatement(statementProductDB);
            helper.returnConnection();
        }
    }

    public Stock getStock(String stockName) throws DAOException {
        Stock stock = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = SELECT_BY_NAME;
        try {
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
            statement.setString(1, stockName);
            rs = statement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                byte percentSize = rs.getByte(3);
                Date expireDate = rs.getDate(4);
                stock = new Stock(id, name, percentSize, expireDate);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
            return stock;
        }
    }

    public Stock getStock(int stockId) throws DAOException {
        Stock stock = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String query = SELECT_BY_ID;
        try {
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
            statement.setInt(1, stockId);
            rs = statement.executeQuery();
            if (rs.next()) {
                int id = stockId;
                String name = rs.getString(2);
                byte percentSize = rs.getByte(3);
                Date expireDate = rs.getDate(4);
                stock = new Stock(id, name, percentSize, expireDate);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
            return stock;
        }
    }

}
