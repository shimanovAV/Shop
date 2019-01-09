package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin.Uppload;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class ChangeProduct implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
            Product product = null;
            String page = req.getParameter("page");
            HttpSession session = req.getSession();
            //нужна ли тут проверка классов?
            Boolean change = (Boolean) session.getAttribute("change");
            int id = (Integer)session.getAttribute("productId");
            try {
                if (change) {
                    String name = req.getParameter("name");
                    String description = req.getParameter("description");
                    String category = req.getParameter("category");
                    double price = Double.parseDouble(req.getParameter("price"));
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    Part part = req.getPart("getFile");
                    String pathToPicture = Uppload.picture(part,req);
                    String dateString = req.getParameter("addingDate");
                    Locale locale = new Locale(req.getSession().getAttribute("language").toString());
                    DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, locale);
                    Date addingDate = format.parse(dateString);
                    product = new Product(id, name, description, category, price, quantity, addingDate, pathToPicture);
                    ServiceFactory serviceFactory = ServiceFactory.getInstance();
                    ProductService productService = serviceFactory.getProductService();
                    if (productService.update(product)) {
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
