package by.etc.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectorDB {

    public static final String URL_TO_DATABASE = "jdbc:mysql://localhost:3306/internetshop";
    public static final String USER_DATABASE = "root";
    public static final String PASSWORD_FOR_DATABASE = "iratar07";
    public static final String PATH_TO_DRIVER = "com.mysql.jdbc.Driver";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(PATH_TO_DRIVER);
        return DriverManager.getConnection(URL_TO_DATABASE, USER_DATABASE, PASSWORD_FOR_DATABASE);
    }
}
