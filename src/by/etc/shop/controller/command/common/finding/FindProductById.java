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
    public static final String PRODUCT_ID_PARAM = "productID";
    public static final String PRODUCT_ATTRIBUTE = "product";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            int productID = Integer.parseInt(req.getParameter(PRODUCT_ID_PARAM));
            Product product = productService.getProductById(productID);
            if (product != null) {
                session.setAttribute(PRODUCT_ATTRIBUTE, product);
                RequestDispatcher dispatcher = req.getRequestDispatcher(PRODUCT_PAGE);
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IOException | ServletException | ServiceException e) {
            throw new CommandException(e);
        }
    }
}
