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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChangeStock implements Command {
    public static final String ADMIN_SALE_PAGE = "/adminSales";
    public static final String STOCK_ID_PARAM = "stockID";
    public static final String NAME_PARAM = "name";
    public static final String PERCENT_SIZE_PARAM = "percentSize";
    public static final String EXPIRE_DATE_PARAM = "expireDate";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        Stock stock;
        try {
            int stockID = Integer.parseInt(req.getParameter(STOCK_ID_PARAM));
            String name = req.getParameter(NAME_PARAM);
            byte percentSize = Byte.parseByte(req.getParameter(PERCENT_SIZE_PARAM));
            String dateString = req.getParameter(EXPIRE_DATE_PARAM);
            Date expireDate = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
            stock = new Stock(stockID, name, percentSize, expireDate);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            StockService stockService = serviceFactory.getStockService();
            if (stockService.update(stock)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_SALE_PAGE);
                Catalog.CATALOG.putIn(req.getSession());
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException | ServletException | ParseException e) {
            throw new CommandException(e);
        }
    }

}
