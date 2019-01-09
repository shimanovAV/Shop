package by.etc.shop.controller.command.user;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Basket;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;

public class DeleteFromBasket implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Basket basket = null;
            String page = req.getParameter("page");
            int productId = Integer.parseInt(req.getParameter("productId"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            basket = new Basket(productId, userId);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            BasketService basketService = serviceFactory.getBasketService();

            if (basketService.deleteFromBasket(basket)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}
