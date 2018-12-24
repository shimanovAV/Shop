package by.etc.shop.controller.command.admin_command.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin_command.Uppload;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.ServiceFactory;

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

public class AddProduct implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            String page = req.getParameter("page");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));
            Part part = req.getPart("getFile");
            String pathToPicture = Uppload.picture(part);
            String dateString = req.getParameter("addingDate");
            Locale locale = new Locale(req.getSession().getAttribute("language").toString());
            DateFormat format = DateFormat.getDateInstance(DateFormat.FULL, locale);
            Date addingDate = format.parse(dateString);
            Product product = new Product(name, description, category, price, quantity, addingDate, pathToPicture);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
           if(productService.add(product)){
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
