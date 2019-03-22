package by.etc.shop.controller.command.user.order;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
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
    public static final String BASKET_PARAM = "basket";
    public static final String SUMM_PARAM = "summ";
    public static final String USER_ID_PARAM = "userId";
    public static final String COURIER_PARAM = "courier";
    public static final String NOT_ACCEPTED_ATTRIBUTE = "notAccepted";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            List<Product> products = (List<Product>) session.getAttribute(BASKET_PARAM);
            Date orderDate = new Date();
            double summ = Double.parseDouble(req.getParameter(SUMM_PARAM));
            String userId = req.getParameter(USER_ID_PARAM);
            boolean courier = Boolean.parseBoolean(req.getParameter(COURIER_PARAM));
            Order order = new Order(orderDate, summ, userId, courier);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            req.setAttribute(NOT_ACCEPTED_ATTRIBUTE, orderService.makeOrder(order, products));
            Catalog.CATALOG.putOrderIn(session);
            RequestDispatcher dispatcher = req.getRequestDispatcher(ORDER_PAGE);
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

}
