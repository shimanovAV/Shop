package by.etc.shop.controller.command.admin.order;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.order.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SendOrder implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try{
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            OrderService orderService = serviceFactory.getOrderService();
            int orderID = Integer.parseInt(req.getParameter("orderId"));
            if(orderService.sendOrder(orderID)){
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.execute(req, resp);
            } else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }

}
