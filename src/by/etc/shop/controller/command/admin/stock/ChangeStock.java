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

public class ChangeStock implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        Stock stock = null;
        String page = req.getParameter("page");
        HttpSession session = req.getSession();
        //нужна ли тут проверка классов?
        Boolean change = (Boolean) session.getAttribute("change");
        int id = (Integer)session.getAttribute("stockId");
        try {
            if (change) {
                String name = req.getParameter("name");
                byte percentSize = Byte.parseByte(req.getParameter("percentByte"));
                String dateString = req.getParameter("expireDate");
                Locale locale = new Locale(req.getSession().getAttribute("language").toString());
                DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, locale);
                Date expireDate = format.parse(dateString);
                stock = new Stock(id, name, percentSize, expireDate);
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                StockService stockService = serviceFactory.getStockService();
                if (stockService.update(stock)) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                    if (dispatcher != null) {
                        dispatcher.forward(req, resp);
                    }
                } else {
                    session.setAttribute("error", true);
                    resp.sendRedirect("anotherPage");
                }
            }
        }catch (ServiceException | IOException | ServletException | ParseException e) {
            throw new CommandException(e);
        }
    }

}
