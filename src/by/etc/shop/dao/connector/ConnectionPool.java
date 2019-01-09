package by.etc.shop.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public enum ConnectionPool {

    POOL;

    public static final int POOL_SIZE = 10;
    private BlockingQueue<Connection> activePool;
    private BlockingQueue<Connection> passivePool;
        private static final String URL = "jdbc:mysql://localhost:3306/shop_schema";
        private static final String USER = "root" ;
        private static final String PASSWORD = "iratar07";
    public static final String PATH_TO_DRIVER = "com.mysql.jdbc.Driver";

        public ConnectionPool createPool() throws ConnectionException {
            try {
                Class.forName(PATH_TO_DRIVER);
                activePool = new ArrayBlockingQueue<Connection>(POOL_SIZE, true);
                passivePool = new ArrayBlockingQueue<Connection>(POOL_SIZE, true);
                for (int i = 0; i < POOL_SIZE; i++) {
                    passivePool.add(DriverManager.getConnection(URL, USER, PASSWORD));
                }
            }catch (SQLException|ClassNotFoundException e) {
                    throw new ConnectionException(e);
                }
            return this;
        }

        public Connection getConnection() throws ConnectionException {
            Connection connection = null;
            try {
                connection = passivePool.take();
                activePool.put(connection);
            } catch (InterruptedException e) {
                throw new ConnectionException(e);
            }
            return connection;
        }

        public void returnConnection(Connection connection)throws ConnectionException{
            activePool.remove(connection);
            try {
                passivePool.put(connection);
            } catch (InterruptedException e){
                throw new ConnectionException(e);
            }
        }

    public boolean isActive(Connection connection) {
        return this.activePool.contains(connection);
    }

        public void deleteConnections(Connection connection)throws ConnectionException {
            while ((connection = passivePool.poll())!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new ConnectionException(e);
                }
            }
            while ((connection = activePool.poll())!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new ConnectionException(e);
                }
            }
        }
    }

