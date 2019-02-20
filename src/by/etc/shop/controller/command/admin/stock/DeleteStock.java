package by.etc.shop.controller.command.admin.stock;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
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
    public static final String ADMIN_SALES_PAGE = "/adminSales";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try{
            String page;
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            StockService stockService = serviceFactory.getStockService();
            int stockID = Integer.parseInt(req.getParameter("stockID"));
            if(stockService.delete(stockID)){
                page=ADMIN_SALES_PAGE;
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                Catalog.CATALOG.putStocksIn(req.getSession());
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException| ServletException e) {
            throw new CommandException(e);
        }
    }

}
