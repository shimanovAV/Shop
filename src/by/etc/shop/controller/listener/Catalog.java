package by.etc.shop.controller.listener;

import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;
import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.like.LikeService;
import by.etc.shop.service.order.OrderService;
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
    List<Product> likeRepository = new LinkedList<>();
    List<Stock> stockRepository = new LinkedList<>();
    Map<Order, List<Product>> orderRepository = new HashMap<>();
    ServiceFactory serviceFactory = ServiceFactory.getInstance();
    ProductService productService = serviceFactory.getProductService();
    StockService stockService = serviceFactory.getStockService();
    LikeService likeService = serviceFactory.getLikeService();
    OrderService orderService = serviceFactory.getOrderService();

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

    public void putIn(HttpSession session, List<Product> newCatalog) {
            repository.clear();
            repository.addAll(newCatalog);
            session.setAttribute("findCatalog", repository);
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

    public void putLikeIn(HttpSession session) {
        try {
            User user = (User)session.getAttribute("user");
            List<Product> newCatalog = likeService.getAllFromLikes(user.getLogin());
            likeRepository.clear();
            likeRepository.addAll(newCatalog);
            session.setAttribute("likes", likeRepository);
        } catch (ServiceException e){

        }
    }

    public void putOrderIn(HttpSession session) {
        try {
            User user = (User)session.getAttribute("user");
            Map<Order, List<Product>> newCatalog = orderService.allOrdersForOne(user.getLogin());
            orderRepository.clear();
            orderRepository.putAll(newCatalog);
            session.setAttribute("orders", orderRepository);
        } catch (ServiceException e){

        }
    }
}
