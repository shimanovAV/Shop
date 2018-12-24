package by.etc.shop.service;

import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.product.ProductServiceImpl;
import by.etc.shop.service.user.UserService;
import by.etc.shop.service.user.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService clientService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();

    private ServiceFactory(){}
    public static ServiceFactory getInstance(){
        return instance;
    }
    public UserService getClientService(){
        return clientService;
    }
    public ProductService getProductService(){
        return productService;
    }
}
