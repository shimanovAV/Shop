package by.etc.shop.dao;

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

        public ConnectionPool createPool() {
            try{
            Class.forName(PATH_TO_DRIVER);}
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            activePool= new ArrayBlockingQueue<Connection>(POOL_SIZE, true);
            passivePool = new ArrayBlockingQueue<Connection>(POOL_SIZE, true);
            for (int i = 0; i < POOL_SIZE; i++) {
                try {
                    passivePool.add(DriverManager.getConnection(URL, USER, PASSWORD));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return this;
        }

        public Connection getConnection() {
            Connection connection = null;
            try {
                connection = passivePool.take();
                activePool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return connection;
        }

        public void deleteConnection(Connection connection){
            activePool.remove(connection);
            try {
                passivePool.put(connection);
            } catch (InterruptedException e){

            }
        }

        public void closeConnections(Connection connection) {
            while ((connection = passivePool.poll())!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            while ((connection = activePool.poll())!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

