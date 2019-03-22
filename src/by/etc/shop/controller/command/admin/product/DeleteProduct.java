package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin.Picture;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProduct implements Command {
    public static final String ADMIN_PAGE = "/Admin";
    public static final String PRODUCT_ID_PARAM = "productID";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Picture.PICTURE.setServletContext(req.getServletContext());
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            int productID = Integer.parseInt(req.getParameter(PRODUCT_ID_PARAM));
            if (productService.delete(productID)) {
                RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_PAGE);
                Catalog.CATALOG.putIn(req.getSession());
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

}
