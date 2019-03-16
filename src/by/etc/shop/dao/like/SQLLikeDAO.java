package by.etc.shop.dao.like;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.entity.Like;
import by.etc.shop.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLLikeDAO implements LikeDAO {
    public static final String INSERT_ALL_FIELDS = "INSERT INTO heart(productId, userId)" +
            " VALUES(?,?)";
    public static final String DELETE_ALL_ID= "delete from heart where productId = ? and userId=? LIMIT 1";
    public static final String SELECT_PRODUCTID = "select productId from heart where userId = ?";
    /*public static final String SELECT_ALL_FOR_ONE_USER= "select prod.id, prod.name, prod.price, " +
            "prod.adding_date, prod.stock_name, prod.old_price, prod.path_to_image, " +
            "bask.quantity from ( select *, row_number()over(order by productId) npp " +
            "from basket where userId=?)bask left join(select *, row_number()over(order by id) npp " +
            "from  product where id=28)prod  on prod.npp=bask.npp";*/
    public static final String SELECT_PRODUCT = "select * from product where id = ?";
    public static final String SELECT_FROM_LIKE_WHERE_ID= "select * from heart where productId = ? and userId = ? ";


    private DataBaseHelper helper = new DataBaseHelper();
    @Override
    public boolean add(Like like) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setInt(1, like.getProductId());
            statement.setString(2, like.getUserId());
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

    public boolean delete(Like like) throws DAOException{
        PreparedStatement statement=null;
        try {
            helper.setQuery(DELETE_ALL_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, like.getProductId());
            statement.setString(2, like.getUserId());
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
        try{
            helper.setQuery(SELECT_PRODUCTID);
            statement = helper.getPreparedStatement();
            statement.setString(1, userLogin);
            rs = statement.executeQuery();
            while(rs.next()) {
                helper.setQuery(SELECT_PRODUCT);
                statement = helper.getPreparedStatement();
                statement.setInt(1, rs.getInt(1));
                ResultSet rs1 = statement.executeQuery();
                if (rs1.next()) {
                    Product product;
                    int id = rs1.getInt(1);
                    String name = rs1.getString(2);
                    String description = rs1.getString(3);
                    String category = rs1.getString(4);
                    double price = rs1.getDouble(5);
                    int quantity = rs1.getInt(6);
                    Date addingDate = rs1.getDate(7);
                    String stockName = rs1.getString(8);
                    double oldPrice = rs1.getDouble(9);
                    String pathToPicture = rs1.getString(10);
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

    public boolean hasLike(Like like) throws DAOException{
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            helper.setQuery(SELECT_FROM_LIKE_WHERE_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, like.getProductId());
            statement.setString(2, like.getUserId());
            rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            this.helper.closeStatement(statement);
            this.helper.returnConnection();
        }
    }
}
