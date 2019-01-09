package by.etc.shop.service;

import by.etc.shop.entity.Basket;
import by.etc.shop.service.basket.BasketService;
import by.etc.shop.service.basket.BasketServiceImpl;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.product.ProductServiceImpl;
import by.etc.shop.service.stock.StockService;
import by.etc.shop.service.stock.StockServiceImpl;
import by.etc.shop.service.user.UserService;
import by.etc.shop.service.user.UserServiceImpl;

public final class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();
    private final UserService clientService = new UserServiceImpl();
    private final ProductService productService = new ProductServiceImpl();
    private final StockService stockService = new StockServiceImpl();
    private final BasketService basketService = new BasketServiceImpl();

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
    public StockService getStockService(){
        return stockService;
    }
    public BasketService getBasketService(){
        return basketService;
    }
}
