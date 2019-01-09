package by.etc.shop.dao.product;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.entity.Product;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLProductDAO implements ProductDAO {
    public static final String INSERT_ALL_FIELDS = "INSERT INTO product(name, description, category, price, quantity, " +
            "adding_date, path_to_image) VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_ALL_ID= "delete from product where id = ?";
    public static final String SELECT_ALL= "select * from product";
    public static final String UPDATE_WHERE_ID= "UPDATE product SET name=?," +
            "description = ?, category = ?, price = ?," +
            " quantity = ?, adding_date = ?, path_to_image = ? WHERE id = ?";
    private DataBaseHelper helper = new DataBaseHelper();
    @Override
    public boolean add(Product product) throws DAOException {
        PreparedStatement statement = null;
        try {
            helper.setQuery(INSERT_ALL_FIELDS);
            statement = helper.getPreparedStatement();
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getCategory());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantity());
            java.sql.Date sqlDate = new java.sql.Date(product.getAddingDate().getTime());
            statement.setDate(6,sqlDate);
            statement.setString(7, product.getPath());
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

    public boolean delete(Product product) throws DAOException{
        PreparedStatement statement=null;
        try {
            helper.setQuery(DELETE_ALL_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, product.getId());
            statement.executeUpdate();
            String pathToPicture = product.getPath();
            File file = new File(pathToPicture);
            if(file.delete()){
            return true;
            }else{
                throw new DAOException("Picture hasn't been deleted");
            }
        } catch(SQLException e){
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public List<Product> allProduct() throws DAOException{
        PreparedStatement statement=null;
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        String query = SELECT_ALL;
        try{
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
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
    public boolean update(Product product) throws DAOException{
        PreparedStatement statement = null;
        try {
            helper.setQuery(UPDATE_WHERE_ID);
            statement = helper.getPreparedStatement();
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getCategory());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, product.getQuantity());
            java.sql.Date sqlDate = new java.sql.Date(product.getAddingDate().getTime());
            statement.setDate(6,sqlDate);
            statement.setString(7, product.getPath());
            statement.setInt(8, product.getId());
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
