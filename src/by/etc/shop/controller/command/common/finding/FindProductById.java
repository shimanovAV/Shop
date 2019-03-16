package by.etc.shop.controller.command.common.finding;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FindProductById implements Command {
    public static final String PRODUCT_PAGE = "/Product";
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page = null;

        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            int productID = Integer.parseInt(req.getParameter("productID"));
            Product product = productService.getProductById(productID);
            if(product!=null){
                page=PRODUCT_PAGE;
                req.setAttribute("product", product);
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
           } catch (IOException| ServletException | ServiceException e) {
            throw new CommandException(e);
        }
    }
}
