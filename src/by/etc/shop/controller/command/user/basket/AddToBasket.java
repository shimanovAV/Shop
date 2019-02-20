package by.etc.shop.controller.command.user.basket;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Basket;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddToBasket implements Command {
    public static final String BASKET_PAGE = "/BasketProduct";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Basket basket = null;
            String page = req.getParameter("page");
            int productId = Integer.parseInt(req.getParameter("productId"));
            String userId = req.getParameter("userId");
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            basket = new Basket(productId, userId, quantity);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BasketService basketService = serviceFactory.getBasketService();
            if(basketService.addToBasket(basket)){
                BasketProduct basketProduct = new BasketProduct();
                basketProduct.execute(req,resp);
            }
            else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }

}
