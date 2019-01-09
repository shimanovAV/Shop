package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin.Uppload;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    public static final String ADMIN_PAGE = "/Admin";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            String page = req.getParameter("page");
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String category = req.getParameter("category");
            double price = Double.parseDouble(req.getParameter("price"));
            int quantity = Integer.parseInt(req.getParameter("quantity"));

            Part part = req.getPart("getFile");
            String pathToPicture = Uppload.picture(part, req);

            int lastIndex = pathToPicture.lastIndexOf("\\");
            pathToPicture = pathToPicture.substring(lastIndex+1,pathToPicture.length());
            pathToPicture = ".//pictures//" + pathToPicture;
            Date addingDate = new Date();
            Product product = new Product(name, description, category, price, quantity, addingDate, pathToPicture);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
           if(productService.add(product)){
               page=ADMIN_PAGE;
               RequestDispatcher dispatcher = req.getRequestDispatcher(page);
               Catalog.CATALOG.putIn(req);
               if (dispatcher != null) {
                   dispatcher.forward(req, resp);
               }
           }
           else{
               HttpSession session = req.getSession();
               session.setAttribute("error", true);
               resp.sendRedirect("anotherPage");
           }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}
