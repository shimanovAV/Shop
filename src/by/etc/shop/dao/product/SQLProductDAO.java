package by.etc.shop.dao.product;

import by.etc.shop.controller.command.admin.Picture;
import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.entity.Product;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLProductDAO implements ProductDAO {
    public static final String INSERT_ALL_FIELDS = "INSERT INTO product(name, description, category, price, quantity, " +
            "adding_date, path_to_image) VALUES(?,?,?,?,?,?,?)";
    public static final String DELETE_ALL_ID = "delete from product where id = ?";
    public static final String DELETE_PRODUCT_IN_BASKET = "delete from basket where productId = ?";
    public static final String DELETE_PRODUCT_IN_LIKE = "delete from like where productId = ?";
    public static final String SELECT_ALL = "select * from product";
    public static final String UPDATE_WHERE_ID = "UPDATE product SET name=?," +
            "description = ?, category = ?, price = ?," +
            " quantity = ?, adding_date = ?, stock_name = ?, " +
            "old_price = ?, path_to_image = ? WHERE id = ?";
    public static final String SELECT_PATH_BY_ID = "select path_to_image from product where id = ?";
    public static final String SELECT_FROM_PRODUCT_WHERE_ID = "select * from product where id = ?";
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
            statement.setDate(6, sqlDate);
            statement.setString(7, product.getPath());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public boolean delete(int productID) throws DAOException {
        PreparedStatement statementLikeDB = null;
        PreparedStatement statementBasketDB = null;
        PreparedStatement statementProductDB = null;
        ResultSet rs;
        try {
            helper.setQuery(SELECT_PATH_BY_ID);
            statementProductDB = helper.getPreparedStatementManualCon();
            statementProductDB.setInt(1, productID);
            rs = statementProductDB.executeQuery();
            String pathToPicture = null;
            if (rs.next()) {
                pathToPicture = rs.getString(1);
            }
            pathToPicture = Picture.PICTURE.fromDBToServer(pathToPicture);
            File file = new File(pathToPicture);
            if (!file.delete()) {
                throw new DAOException("Picture hasn't been deleted");
            }
            helper.setQuery(DELETE_ALL_ID);
            statementProductDB = helper.getPreparedStatement();
            statementProductDB.setInt(1, productID);
            statementProductDB.executeUpdate();
            helper.setQuery(DELETE_PRODUCT_IN_BASKET);
            statementBasketDB = helper.getPreparedStatementManualCon();
            statementBasketDB.setInt(1, productID);
            statementBasketDB.executeUpdate();
            helper.setQuery(DELETE_PRODUCT_IN_LIKE);
            statementLikeDB = helper.getPreparedStatementManualCon();
            statementLikeDB.setInt(1, productID);
            statementLikeDB.executeUpdate();
            helper.commit(statementLikeDB);
            helper.commit(statementBasketDB);
            helper.commit(statementProductDB);
            return true;
        } catch (SQLException | IOException e) {
            helper.rollBack(statementLikeDB);
            helper.rollBack(statementBasketDB);
            helper.rollBack(statementProductDB);
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statementLikeDB);
            helper.closeStatement(statementBasketDB);
            helper.closeStatement(statementProductDB);
            helper.returnConnection();
        }
    }

    public List<Product> allProduct() throws DAOException {
        PreparedStatement statement = null;
        List<Product> products = new ArrayList<>();
        ResultSet rs = null;
        String query = SELECT_ALL;
        try {
            helper.setQuery(query);
            statement = helper.getPreparedStatement();
            rs = statement.executeQuery();
            while (rs.next()) {
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
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
            return products;
        }
    }

    public boolean update(Product product) throws DAOException {
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
            statement.setDate(6, sqlDate);
            statement.setString(7, product.getStockName());
            statement.setDouble(8, product.getOldPrice());
            statement.setString(9, product.getPath());
            statement.setInt(10, product.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            helper.closeStatement(statement);
            helper.returnConnection();
        }
    }

    public Product getProductById(int productID) throws DAOException {
        PreparedStatement statement = null;
        Product product = null;
        ResultSet rs = null;
        try {
            helper.setQuery(SELECT_FROM_PRODUCT_WHERE_ID);
            statement = helper.getPreparedStatement();
            statement.setInt(1, productID);
            rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString(2);
                String description = rs.getString(3);
                String category = rs.getString(4);
                double price = rs.getDouble(5);
                int quantity = rs.getInt(6);
                Date addingDate = rs.getDate(7);
                String stockName = rs.getString(8);
                double oldPrice = rs.getDouble(9);
                String pathToPicture = rs.getString(10);
                product = new Product(productID, name, description, category, price, quantity,
                        addingDate, stockName, oldPrice, pathToPicture);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            this.helper.closeStatement(statement);
            this.helper.returnConnection();
            return product;
        }
    }
}

