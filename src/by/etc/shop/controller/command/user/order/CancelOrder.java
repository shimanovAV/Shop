package by.etc.shop.controller.command.user.order;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.order.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CancelOrder implements Command {

    public static final String PAGE = "/OrderPage";
    public static final String ORDER_ID_PARAM = "orderId";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            int orderId = Integer.parseInt(req.getParameter(ORDER_ID_PARAM));
            if (orderService.cancelOrder(orderId)) {
                Catalog.CATALOG.putOrderIn(session);
                RequestDispatcher dispatcher = req.getRequestDispatcher(PAGE);
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

}
