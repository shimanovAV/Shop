package by.etc.shop.controller.command.user.basket;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Basket;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToBasket implements Command {


    public static final String PRODUCT_ID_PARAM = "productId";
    public static final String USER_ID_PARAM = "userId";
    public static final String QUANTITY_PARAM = "quantity";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Basket basket;
            int productId = Integer.parseInt(req.getParameter(PRODUCT_ID_PARAM));
            String userId = req.getParameter(USER_ID_PARAM);
            int quantity = Integer.parseInt(req.getParameter(QUANTITY_PARAM));
            basket = new Basket(productId, userId, quantity);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BasketService basketService = serviceFactory.getBasketService();
            if (basketService.addToBasket(basket)) {
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
