package by.etc.shop.controller.command.user.order;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.order.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class MakeOrder implements Command {
    public static final String ORDER_PAGE = "/OrderPage";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            List<Product> products = (List<Product>) session.getAttribute("basket");
            Date orderDate = new Date();
            double summ = Double.parseDouble(req.getParameter("summ"));
            String userId = req.getParameter("userId");
            boolean courier = Boolean.parseBoolean(req.getParameter("courier"));
            Order order = new Order(orderDate, summ, userId, courier);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            req.setAttribute("notAccepted", orderService.makeOrder(order, products));
            Catalog.CATALOG.putOrderIn(session);
            RequestDispatcher dispatcher = req.getRequestDispatcher(ORDER_PAGE);
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            } else {
                session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

}
