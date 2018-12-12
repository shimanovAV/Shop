package by.etc.shop.dao;
import by.etc.shop.entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseHelper {
    private Connection connect;
    private String query;

    public DataBaseHelper() throws DAOException {
            ConnectionPool pool = ConnectionPool.POOL.createPool();
        connect = pool.getConnection();
    }


    public DataBaseHelper(String query) throws DAOException{
            ConnectionPool pool = ConnectionPool.POOL.createPool();
            connect = pool.getConnection();
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

    public boolean insertUser(PreparedStatement ps, User user) throws DAOException{
        boolean flag = false;
        try {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return flag;
    }

    public User getUser(PreparedStatement ps, String login, String password) throws DAOException {
        User user = null;
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (login.equals(rs.getString(3))) {
                    if (password.equals(rs.getString(4))) {
                        int id = rs.getInt(1);
                        String userName = rs.getString(2);
                        String parLogin = rs.getString(3);
                        String parPassword = rs.getString(4);
                        String email = rs.getString(5);
                        user = new User(id, userName, parLogin, parPassword, email);
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return user;
    }

    public boolean hasUser(PreparedStatement ps, String login) throws DAOException{
        boolean flag = false;
        try {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (login.equals(rs.getString(3))) {
                    flag = true;
                    }
                }
            } catch (SQLException e) {
            throw new DAOException(e);
        }
            return flag;
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
}
