package by.etc.shop.controller.command.user.basket;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;
import by.etc.shop.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BasketProduct implements Command {
    public static final String BASKET_PAGE = "/BasketPage";
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = (User)req.getSession().getAttribute("user");
            String userLogin = user.getLogin();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BasketService basketService = serviceFactory.getBasketService();
            List<Product> basket = basketService.getAllFromBasket(userLogin);
            if(basket!=null){
                req.setAttribute("basket", basket);
                RequestDispatcher dispatcher = req.getRequestDispatcher(BASKET_PAGE);
                if(dispatcher!=null){
                    dispatcher.forward(req, resp);
                }
            }
            else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException| ServletException | IOException e){
            throw new CommandException(e);
        }
    }
}
