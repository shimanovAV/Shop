package by.etc.shop.dao;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO sqlUserImpl = new SQLUserDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDAO getUserDAO(){
        return sqlUserImpl;
    }
}
