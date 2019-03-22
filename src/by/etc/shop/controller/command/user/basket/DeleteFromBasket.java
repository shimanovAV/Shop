package by.etc.shop.controller.command.user.basket;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteFromBasket implements Command {

    public static final String PRODUCT_ID_PARAM = "productID";
    public static final String PRODUCT_QUANTITY_PARAM = "productQuantity";
    public static final String USER_PARAM = "user";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Basket basket;
            HttpSession session = req.getSession();
            int productId = Integer.parseInt(req.getParameter(PRODUCT_ID_PARAM));
            int quantity = Integer.parseInt(req.getParameter(PRODUCT_QUANTITY_PARAM));
            User user = (User) session.getAttribute(USER_PARAM);
            String userLogin = user.getLogin();
            basket = new Basket(productId, userLogin, quantity);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BasketService basketService = serviceFactory.getBasketService();
            if (basketService.deleteFromBasket(basket)) {
                BasketProduct basketProduct = new BasketProduct();
                basketProduct.execute(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }
}
