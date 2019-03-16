package by.etc.shop.dao.order;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DataBaseHelper;
import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SQLOrderDAO implements OrderDAO {

    public static final String INSERT_INTO_ORDER = "INSERT INTO orders(orderDate, sum, userId, courier) VALUES(?,?,?,?)";
    public static final String INSERT_INTO_PRODUCT_ORDER = "INSERT INTO productorders(orderId, productId, quantity)" +
            " VALUES(?,?,?)";
    public static final String DELETE_ORDER = "delete from orders where id = ?";
    public static final String DELETE_PRODUCT_ORDER = "delete from productorders where orderId = ?";
    public static final String SELECT_ID = "select id from orders WHERE orderDate = ? and sum = ? and userId = ? and courier = ? order by id asc";
    public static final String SELECT_CURRENT_QUANTITY = "select quantity from product where id = ?";
    public static final String CHANGE_QUANTITY_OF_PRODUCT = "UPDATE product SET quantity = quantity - ? WHERE id  = ?";
    public static final String DELETE_FROM_BASKET= "delete from basket where productId = ? and userId=? and quantity=? LIMIT 1";
    public static final String SELECT_PRODUCT = "select t1.*, t2.quantity from product as t1" +
            " inner join productorders as t2 on t1.id = t2.productId where t2.productId in(select productId from productorders where orderId = ?) and t2.orderId = ?";
    public static final String SELECT_PRODUCT_IN_ORDER = "select productId, quantity from productorders where orderId = ?";
    public static final String SELECT_ALL_USERS_THAT_ORDERED = "select userId from orders";
    public static final String SELECT_ALL_ORDER_FOR_ONE = "select * from orders where userId = ?";



    private DataBaseHelper helper = new DataBaseHelper();


    @Override
    public boolean add(Order order, List<Product> products) throws DAOException {
        PreparedStatement orderStatement = null;
        PreparedStatement orderStatementForSelect = null;
        PreparedStatement productOrderStatement = null;
        PreparedStatement basketStatement = null;
        ResultSet rs;
        try {
            helper.setQuery(INSERT_INTO_ORDER);
            orderStatement = helper.getPreparedStatementManualCon();
            java.sql.Date sqlDate = new java.sql.Date(order.getOrderDate().getTime());
            orderStatement.setDate(1, sqlDate);
            orderStatement.setDouble(2, order.getSumm());
            orderStatement.setString(3, order.getUserId());
            orderStatement.setBoolean(4, order.isCourier());
            orderStatement.executeUpdate();
            helper.setQuery(SELECT_ID);
            orderStatementForSelect = helper.getPreparedStatementManualCon();
            orderStatementForSelect.setDate(1, sqlDate);
            orderStatementForSelect.setDouble(2, order.getSumm());
            orderStatementForSelect.setString(3, order.getUserId());
            orderStatementForSelect.setBoolean(4, order.isCourier());
            rs = orderStatementForSelect.executeQuery();
            if(rs.next()){
                helper.setQuery(INSERT_INTO_PRODUCT_ORDER);
                productOrderStatement = helper.getPreparedStatementManualCon();
                helper.setQuery(DELETE_FROM_BASKET);
                basketStatement = helper.getPreparedStatementManualCon();
                rs.last();
                productOrderStatement.setInt(1, rs.getInt(1));
                Iterator<Product> iterator = products.listIterator();
                while(iterator.hasNext()){
                    Product product = iterator.next();
                    productOrderStatement.setInt(2, product.getId());
                    productOrderStatement.setInt(3, product.getQuantity());
                    basketStatement.setInt(1, product.getId());
                    basketStatement.setString(2, order.getUserId());
                    basketStatement.setInt(3, product.getQuantity());
                    productOrderStatement.executeUpdate();
                    basketStatement.executeUpdate();
                }
            }
            helper.commit(orderStatement);
            helper.commit(orderStatementForSelect);
            helper.commit(productOrderStatement);
            helper.commit(basketStatement);
            return true;
        } catch (SQLException e) {
            helper.rollBack(orderStatement);
            helper.rollBack(orderStatementForSelect);
            helper.rollBack(productOrderStatement);
            helper.rollBack(basketStatement);
            throw new DAOException(e);
        } finally {
            helper.closeStatement(orderStatement);
            helper.closeStatement(orderStatementForSelect);
            helper.closeStatement(productOrderStatement);
            helper.closeStatement(basketStatement);
            helper.returnConnection();
        }
    }

    public boolean delete(int orderId) throws DAOException {
        PreparedStatement orderStatement = null;
        PreparedStatement productOrderStatement = null;
        try {
            helper.setQuery(DELETE_ORDER);
            orderStatement = helper.getPreparedStatementManualCon();
            orderStatement.setInt(1, orderId);
            orderStatement.executeUpdate();
            helper.setQuery(DELETE_PRODUCT_ORDER);
            productOrderStatement = helper.getPreparedStatementManualCon();
            productOrderStatement.setInt(1, orderId);
            productOrderStatement.executeUpdate();
            helper.commit(orderStatement);
            helper.commit(productOrderStatement);
            return true;
        } catch (SQLException e) {
            helper.rollBack(orderStatement);
            helper.rollBack(productOrderStatement);
            throw new DAOException(e);
        } finally {
            helper.closeStatement(orderStatement);
            helper.closeStatement(productOrderStatement);
            helper.returnConnection();
        }
    }

    public List<Product> allProductBy(int orderId) throws DAOException{
        PreparedStatement statement=null;
        List<Product> products = new ArrayList<>();
        try{
                helper.setQuery(SELECT_PRODUCT);
                statement = helper.getPreparedStatement();
                statement.setInt(1, orderId);
                statement.setInt(2, orderId);
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Product product = null;
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);
                    String category = rs.getString(4);
                    double price = rs.getDouble(5);
                    Date addingDate = rs.getDate(7);
                    String stockName = rs.getString(8);
                    double oldPrice = rs.getDouble(9);
                    String pathToPicture = rs.getString(10);
                    int quantity = rs.getInt(11);
                    product = new Product(id, name, description, category, price, quantity,
                            addingDate, stockName, oldPrice, pathToPicture);
                    products.add(product);
                }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            return products;
        }
    }

    public Map<Order, List<Product>> allProduct() throws DAOException{
        PreparedStatement statement=null;
        Map<Order, List<Product>> orders = new HashMap<>();
        try{
            helper.setQuery(SELECT_ALL_USERS_THAT_ORDERED);
            statement = helper.getPreparedStatement();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
               Map<Order, List<Product>> allOrdersForOne = allOrdersForOne(rs.getString(1));
               orders.putAll(allOrdersForOne);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
            return orders;
        }
    }

    public Map<Order, List<Product>> allOrdersForOne(String userLogin) throws DAOException{
        PreparedStatement statement=null;
        Map<Order, List<Product>> orders = new HashMap<>();
        try{
            helper.setQuery(SELECT_ALL_ORDER_FOR_ONE);
            statement = helper.getPreparedStatement();
            statement.setString(1, userLogin);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                List<Product> products;
                int id = rs.getInt(1);
                Date orderDate = rs.getDate(2);
                double summ = rs.getDouble(3);
                String userId = rs.getString(4);
                boolean courier = rs.getBoolean(5);
                Order order = new Order(id, orderDate, summ, userId, courier);
                products = allProductBy(id);
                orders.put(order, products);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }finally
        {
            helper.closeStatement(statement);
            helper.returnConnection();
            return orders;
        }
    }

    public boolean send(int orderId) throws DAOException {
        PreparedStatement productStatement = null;
        PreparedStatement changeProductStatement = null;
        ResultSet rs;
        try {
            helper.setQuery(SELECT_PRODUCT_IN_ORDER);
            productStatement = helper.getPreparedStatementManualCon();
            productStatement.setInt(1, orderId);
            rs = productStatement.executeQuery();
            helper.setQuery(SELECT_CURRENT_QUANTITY);
            changeProductStatement = helper.getPreparedStatementManualCon();
            while(rs.next()){
                changeProductStatement.setInt(1, rs.getInt(1));
                ResultSet rs1 = changeProductStatement.executeQuery();
                if(rs1.next()){
                    if(rs1.getInt(1)>=rs.getInt(2)){
                    helper.setQuery(CHANGE_QUANTITY_OF_PRODUCT);
                    changeProductStatement = helper.getPreparedStatementManualCon();
                    changeProductStatement.setInt(1, rs.getInt(2));
                    changeProductStatement.setInt(2, rs.getInt(1));
                    changeProductStatement.executeUpdate();
                } else{
                    throw new DAOException("There are not enough products");
                }
            }

            }
            delete(orderId);
            helper.commit(changeProductStatement);
            helper.commit(productStatement);
            return true;
        } catch (SQLException e) {
            helper.rollBack(changeProductStatement);
            helper.rollBack(productStatement);
            throw new DAOException(e);
        } finally {
            helper.closeStatement(productStatement);
            helper.closeStatement(changeProductStatement);
            helper.returnConnection();
        }
    }

}
