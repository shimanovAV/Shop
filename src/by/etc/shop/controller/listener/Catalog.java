package by.etc.shop.controller.listener;

import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;
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
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    ProductService productService = serviceFactory.getProductService();

   Catalog() {
       try {
           repository.addAll(productService.getAll());
       } catch (ServiceException e){
           //Что делать?
       }
    }

    public void putIn(HttpServletRequest req) {
        try {
                List<Product> newCatalog = productService.getAll();
                repository.clear();
                repository.addAll(newCatalog);
                req.getSession().setAttribute("catalog", repository);
        } catch (ServiceException e){

        }
    }
}
