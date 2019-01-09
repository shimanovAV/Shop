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
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class AddStock implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            String page = req.getParameter("page");
            String name = req.getParameter("name");
            byte percentSize = Byte.parseByte(req.getParameter("percentSize"));
            String dateString = req.getParameter("expireDate");
            Locale locale = new Locale(req.getSession().getAttribute("language").toString());
            DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, locale);
            Date expireDate = format.parse(dateString);
            Stock stock = new Stock(name, percentSize, expireDate);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            StockService stockService = serviceFactory.getStockService();
            if(stockService.add(stock)){
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                if(dispatcher!=null){
                    dispatcher.forward(req, resp);
                }
            }
            else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException | ServletException | ParseException e) {
            throw new CommandException(e);
        }
    }

}
