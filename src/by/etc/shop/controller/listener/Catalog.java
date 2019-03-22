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


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public enum Catalog {

    CATALOG;

    public static final String ORDERS_ATTRIBUTE = "orders";
    public static final String USER_PARAM = "user";
    public static final String LIKES_ATTRIBUTE = "likes";
    public static final String STOCKS_PARAM = "stocks";
    public static final String CATALOG_ATTRIBUTE = "catalog";

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
    }

    public void putIn(HttpSession session) {
        try {
            List<Product> newCatalog = productService.getAll();
            repository.clear();
            repository.addAll(newCatalog);
            session.setAttribute(CATALOG_ATTRIBUTE, repository);
        } catch (ServiceException e) {
            repository = null;
        }
    }

    public void putStocksIn(HttpSession session) {
        try {
            List<Stock> newCatalog = stockService.getAll();
            stockRepository.clear();
            stockRepository.addAll(newCatalog);
            session.setAttribute(STOCKS_PARAM, stockRepository);
        } catch (ServiceException e) {
            stockRepository = null;
        }
    }

    public void putLikeIn(HttpSession session) {
        try {
            User user = (User) session.getAttribute(USER_PARAM);
            List<Product> newCatalog = likeService.getAllFromLikes(user.getLogin());
            likeRepository.clear();
            likeRepository.addAll(newCatalog);
            session.setAttribute(LIKES_ATTRIBUTE, likeRepository);
        } catch (ServiceException e) {
            likeRepository = null;
        }
    }

    public void putOrderIn(HttpSession session) {
        try {
            User user = (User) session.getAttribute(USER_PARAM);
            Map<Order, List<Product>> newCatalog = orderService.allOrdersForOne(user.getLogin());
            orderRepository.clear();
            orderRepository.putAll(newCatalog);
            session.setAttribute(ORDERS_ATTRIBUTE, orderRepository);
        } catch (ServiceException e) {
            orderRepository = null;
        }
    }
}
