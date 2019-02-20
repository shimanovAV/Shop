package by.etc.shop.controller.listener;

import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.stock.StockService;
import by.etc.shop.service.user.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum Catalog {

    CATALOG;

    List<Product> repository = new LinkedList<>();
    List<Stock> stockRepository = new LinkedList<>();
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    ProductService productService = serviceFactory.getProductService();
    StockService stockService = serviceFactory.getStockService();

   Catalog() {
       try {
           repository.addAll(productService.getAll());
       } catch (ServiceException e){
           //Что делать?
       }
    }

    public void putIn(HttpSession session) {
        try {
                List<Product> newCatalog = productService.getAll();
                repository.clear();
                repository.addAll(newCatalog);
                session.setAttribute("catalog", repository);
        } catch (ServiceException e){

        }
    }

    public void putStocksIn(HttpSession session) {
        try {
            List<Stock> newCatalog = stockService.getAll();
            stockRepository.clear();
            stockRepository.addAll(newCatalog);
            session.setAttribute("stocks", stockRepository);
        } catch (ServiceException e){

        }
    }

    public void putBasketIn(HttpSession session) {
        try {
            List<Stock> newCatalog = stockService.getAll();
            stockRepository.clear();
            stockRepository.addAll(newCatalog);
            session.setAttribute("stocks", stockRepository);
        } catch (ServiceException e){

        }
    }
}
