package by.etc.shop.controller.command.admin.order;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.order.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendOrder implements Command {

    public static final String ORDER_ID_PARAM = "orderId";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            int orderID = Integer.parseInt(req.getParameter(ORDER_ID_PARAM));
            if (orderService.sendOrder(orderID)) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.execute(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }

}
