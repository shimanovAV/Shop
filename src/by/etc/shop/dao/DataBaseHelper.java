package by.etc.shop.dao;
import by.etc.shop.dao.connector.ConnectionException;
import by.etc.shop.dao.connector.ConnectionPool;
import by.etc.shop.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



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
            if (!ConnectionPool.POOL.isActive(this.connect)) {
                this.connect = ConnectionPool.POOL.getConnection();
            }
            ps = this.connect.prepareStatement(this.query);
            return ps;
        } catch (ConnectionException | SQLException var3) {
            throw new DAOException(var3);
        }
    }

    public PreparedStatement getPreparedStatementManualCon() throws DAOException{
        PreparedStatement ps = null;
        try {
            if (!ConnectionPool.POOL.isActive(this.connect)) {
                this.connect = ConnectionPool.POOL.getManualConnection();
            }
            ps = this.connect.prepareStatement(this.query);
            return ps;
        } catch (ConnectionException | SQLException var3) {
            throw new DAOException(var3);
        }
    }

    public void commit(PreparedStatement ps) throws DAOException{
        try {
            Connection connection = ps.getConnection();
            connection.commit();
        }catch (SQLException e){
            throw new DAOException(e);
        }
    }

    public void rollBack(PreparedStatement ps) throws DAOException{
        try {
            Connection connection = ps.getConnection();
            connection.rollback();
        }catch (SQLException e){
            throw new DAOException(e);
        }
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
