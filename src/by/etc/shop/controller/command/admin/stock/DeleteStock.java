package by.etc.shop.controller.command.admin.stock;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.stock.StockService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteStock implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try{
            Stock stock = null;
            String page = req.getParameter("page");
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            StockService stockService = serviceFactory.getStockService();
            HttpSession session = req.getSession();
            if(session.getAttribute("stock").getClass() == Stock.class){
                stock = (Stock)session.getAttribute("stock");}
            if(stockService.delete(stock)){
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                if(dispatcher!=null){
                    dispatcher.forward(req, resp);
                }
            } else{
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

}
