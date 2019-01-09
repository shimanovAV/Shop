package by.etc.shop.dao.basket;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Product;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLBasketDAO implements BasketDAO {

    public static final String INSERT_ALL_FIELDS = "INSERT INTO basket(productId, userId, quantity)" +
            " VALUES(?,?,?)";
    public static final String DELETE_ALL_ID= "delete from basket where productId = ? and userId=?";
    public static final String SELECT_ALL_FOR_ONE_USER= "select * from product where id=(select productId from basket where userId=?)";
    public static final String UPDATE_WHERE_ID= "UPDATE basket SET quantity=? WHERE productId = ? and " +
            "userId=?";
    private DataBaseHelper helper = new DataBaseHelper();
    @Override
    public boolean add(Basket basket) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setInt(1, basket.getProductId());
            statement.setInt(2, basket.getUserId());
            statement.setInt(3, basket.getQuantity());
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

    public boolean delete(Basket basket) throws DAOException{
        PreparedStatement statement=null;
        try {
            helper.setQuery(DELETE_ALL_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, basket.getProductId());
            statement.setInt(2, basket.getUserId());
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

    public List<Product> allProductInBasket(int userId) throws DAOException{
        PreparedStatement statement=null;
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        String query = SELECT_ALL_FOR_ONE_USER;
        try{
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
            statement.setInt(1, userId);
            statement.executeUpdate();
            rs = statement.executeQuery();
            while(rs.next()){
                Product product = null;
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String category = rs.getString(4);
                double price = rs.getDouble(5);
                int quantity = rs.getInt(6);
                Date addingDate = rs.getDate(7);
                String stockName = rs.getString(8);
                double oldPrice = rs.getDouble(9);
                String pathToPicture = rs.getString(10);
                product = new Product(id, name, description, category, price, quantity,
                        addingDate, stockName, oldPrice, pathToPicture);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
            return products;
        }
    }
    public boolean update(Basket basket) throws DAOException{
        PreparedStatement statement = null;
        try {
            helper.setQuery(UPDATE_WHERE_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, basket.getQuantity());
            statement.setInt(2, basket.getProductId());
            statement.setInt(3, basket.getUserId());
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
