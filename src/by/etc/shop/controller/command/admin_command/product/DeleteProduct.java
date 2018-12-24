package by.etc.shop.controller.command.admin_command.product;

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

public class DeleteProduct implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try{
            Product product = null;
            String page = req.getParameter("page");
            String id = req.getParameter("id");
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            HttpSession session = req.getSession();
            if(session.getAttribute("product").getClass() == Product.class){
            product = (Product)session.getAttribute("product");}
            if(productService.delete(product)){
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                if(dispatcher!=null){
                    dispatcher.forward(req, resp);
                }
            } else{
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException| ServletException e) {
            throw new CommandException(e);
        }
    }

}
