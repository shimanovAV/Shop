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
import java.util.List;

public class FindProduct implements Command {

    public static final String FIND_PAGE = "/FindPage";
    public static final String PRODUCT_INFO_PARAM = "productInfo";
    public static final String FIND_CATALOG_ATTRIBUTE = "findCatalog";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            String productInfo = req.getParameter(PRODUCT_INFO_PARAM);
            List<Product> products = productService.getAllBy(productInfo);
            if (products != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher(FIND_PAGE);
                session.setAttribute(FIND_CATALOG_ATTRIBUTE, products);
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IOException | ServiceException | ServletException e) {
            throw new CommandException(e);
        }
    }
}
