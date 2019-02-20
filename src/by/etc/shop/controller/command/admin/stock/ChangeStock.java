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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ChangeStock implements Command {
    public static final String ADMIN_SALE_PAGE = "/adminSales";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        Stock stock = null;
        int stockID = Integer.parseInt(req.getParameter("stockID"));
        try {
                String name = req.getParameter("name");
                byte percentSize = Byte.parseByte(req.getParameter("percentSize"));
            String dateString = req.getParameter("expireDate");
            Date expireDate=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                stock = new Stock(stockID, name, percentSize, expireDate);
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                StockService stockService = serviceFactory.getStockService();
                if (stockService.update(stock)) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_SALE_PAGE);
                    Catalog.CATALOG.putIn(req.getSession());
                    if (dispatcher != null) {
                        dispatcher.forward(req, resp);
                    }
                }
                else{
                    HttpSession session = req.getSession();
                    session.setAttribute("error", true);
                    resp.sendRedirect("anotherPage");
                }
        }catch (ServiceException | IOException | ServletException | ParseException e) {
            throw new CommandException(e);
        }
    }

}
