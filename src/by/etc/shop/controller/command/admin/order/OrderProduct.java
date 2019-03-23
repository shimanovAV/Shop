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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class OrderProduct implements Command {
    public static final String ORDER_PAGE = "/AdminOrderPage";
    public static final String ORDER_ATTRIBUTE = "ordersForAdmin";
    public static final String ORDER_PAGE_USER = "/OrderPage";
    private static final String USER = "user";
    private static final String NOT_ACCEPTED_COLLECTION = "notAccepted";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            Map<Order, List<Product>> orders = orderService.getAllFromOrder();
            session.setAttribute(ORDER_ATTRIBUTE, orders);
            RequestDispatcher dispatcher;
            String userParam = req.getParameter(USER);
            if(userParam!=null && userParam.equals(USER)){
                dispatcher = req.getRequestDispatcher(ORDER_PAGE_USER);
                session.setAttribute(NOT_ACCEPTED_COLLECTION, null);
            } else{
                dispatcher = req.getRequestDispatcher(ORDER_PAGE);
            }
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
