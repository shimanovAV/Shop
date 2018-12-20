package by.etc.shop.dao;
import by.etc.shop.dao.connector.ConnectionException;
import by.etc.shop.dao.connector.ConnectionPool;
import by.etc.shop.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DataBaseHelper {
    private Connection connect;
    private String query;
    //private static final Logger logger;

    /*static {
        logger = LogManager.getLogger(DataBaseHelper.class);
    }*/


    public DataBaseHelper() {
        try {
            ConnectionPool pool = ConnectionPool.POOL.createPool();
            connect = pool.getConnection();
        }catch(ConnectionException e){
           // logger.log(Level.ERROR, "Connection  problems.");
        }
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public PreparedStatement getPreparedStatement() throws DAOException{
        PreparedStatement ps = null;
        try {
            ps = connect.prepareStatement(query);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return ps;
    }

    public void closeStatement(PreparedStatement ps) throws DAOException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }
    public void returnConnection() throws DAOException {
        if (connect != null) {
            try {
                ConnectionPool.POOL.returnConnection(connect);;
            } catch (ConnectionException e) {
                throw new DAOException(e);
            }
        }
    }
}
