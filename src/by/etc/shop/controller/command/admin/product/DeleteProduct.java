package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin.Picture;
import by.etc.shop.controller.listener.Catalog;
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

public class DeleteProduct implements Command {
    public static final String ADMIN_PAGE = "/Admin";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try{
           Picture.PICTURE.setServletContext(req.getServletContext());
            String page = req.getParameter("page");
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            int productID = Integer.parseInt(req.getParameter("productID"));
            if(productService.delete(productID)){
                page=ADMIN_PAGE;
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                Catalog.CATALOG.putIn(req.getSession());
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
