package by.etc.shop.controller.command.admin.order;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Order;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.order.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class OrderProduct implements Command {
    public static final String ORDER_PAGE = "/AdminOrderPage";
    public static final String ORDER_ATTRIBUTE = "orders";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            Map<Order, List<Product>> orders = orderService.getAllFromOrder();
            req.setAttribute(ORDER_ATTRIBUTE, orders);
            RequestDispatcher dispatcher = req.getRequestDispatcher(ORDER_PAGE);
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}
