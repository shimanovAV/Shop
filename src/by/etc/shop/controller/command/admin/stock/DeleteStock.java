package by.etc.shop.controller.command.admin.stock;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.stock.StockService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStock implements Command {
    public static final String ADMIN_SALES_PAGE = "/adminSales";
    public static final String STOCK_ID_PARAM = "stockID";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            StockService stockService = serviceFactory.getStockService();
            int stockID = Integer.parseInt(req.getParameter(STOCK_ID_PARAM));
            if (stockService.delete(stockID)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_SALES_PAGE);
                Catalog.CATALOG.putStocksIn(req.getSession());
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
