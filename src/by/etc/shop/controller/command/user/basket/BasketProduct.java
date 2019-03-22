package by.etc.shop.controller.command.user.basket;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class BasketProduct implements Command {
    public static final String BASKET_PAGE = "/BasketPage";
    public static final String USER_PARAM = "user";
    public static final String BASKET_ATTRIBUTE = "basket";
    public static final String SUMM_ATTRIBUTE = "summ";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            User user = (User) req.getSession().getAttribute(USER_PARAM);
            String userLogin = user.getLogin();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BasketService basketService = serviceFactory.getBasketService();
            List<Product> basket = basketService.getAllFromBasket(userLogin);
            if (basket != null) {
                session.setAttribute(BASKET_ATTRIBUTE, basket);
                req.setAttribute(SUMM_ATTRIBUTE, Basket.countSumm(basket));
                RequestDispatcher dispatcher = req.getRequestDispatcher(BASKET_PAGE);
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}
