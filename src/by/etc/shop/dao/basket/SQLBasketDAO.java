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
    public static final String DELETE_ALL_ID= "delete from basket where productId = ? and userId=? and quantity=? LIMIT 1";
    public static final String SELECT_PRODUCTID = "select productId, quantity from basket where userId = ?";
    /*public static final String SELECT_ALL_FOR_ONE_USER= "select prod.id, prod.name, prod.price, " +
            "prod.adding_date, prod.stock_name, prod.old_price, prod.path_to_image, " +
            "bask.quantity from ( select *, row_number()over(order by productId) npp " +
            "from basket where userId=?)bask left join(select *, row_number()over(order by id) npp " +
            "from  product where id=28)prod  on prod.npp=bask.npp";*/
    public static final String SELECT_PRODUCT = "select * from product where id = ?";
    public static final String UPDATE_WHERE_ID= "UPDATE basket SET quantity=? WHERE productId = ? and " +
            "userId=? LIMIT 1";
    private DataBaseHelper helper = new DataBaseHelper();
    @Override
    public boolean add(Basket basket) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setInt(1, basket.getProductId());
            statement.setString(2, basket.getUserId());
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
            statement.setString(2, basket.getUserId());
            statement.setInt(3, basket.getQuantity());
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

    public List<Product> allProduct(String userLogin) throws DAOException{
        PreparedStatement statement=null;
        List<Product> products = new ArrayList<>();
        ResultSet rs;
      //  String query = SELECT_ALL_FOR_ONE_USER;
        try{
            helper.setQuery(SELECT_PRODUCTID);
            statement = helper.getPreparedStatement();
            statement.setString(1, userLogin);
            //statement.setString(2, userLogin);
            rs = statement.executeQuery();
            while(rs.next()) {
                helper.setQuery(SELECT_PRODUCT);
                statement = helper.getPreparedStatement();
                statement.setInt(1, rs.getInt(1));
                //statement.setString(2, userLogin);
                ResultSet rs1 = statement.executeQuery();
                if (rs1.next()) {
                    Product product = null;
                    int id = rs1.getInt(1);
                    String name = rs1.getString(2);
                    String description = rs1.getString(3);
                    String category = rs1.getString(4);
                    double price = rs1.getDouble(5);
                    Date addingDate = rs1.getDate(7);
                    String stockName = rs1.getString(8);
                    double oldPrice = rs1.getDouble(9);
                    String pathToPicture = rs1.getString(10);
                    int quantity = rs.getInt(2);
                    product = new Product(id, name, description, category, price, quantity,
                            addingDate, stockName, oldPrice, pathToPicture);
                    products.add(product);
                }
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
            statement.setString(3, basket.getUserId());
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
