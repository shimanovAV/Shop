package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin.Picture;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Date;

public class AddProduct implements Command {
    public static final String ADMIN_PAGE = "/Admin";
    public static final String NAME_PARAM = "name";
    public static final String DESCRIPTION_PARAM = "description";
    public static final String CATEGORY_PARAM = "category";
    public static final String PRICE_PARAM = "price";
    public static final String QUANTITY_PARAM = "quantity";
    public static final String FILE_PARAM = "getFile";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            String name = req.getParameter(NAME_PARAM);
            String description = req.getParameter(DESCRIPTION_PARAM);
            String category = req.getParameter(CATEGORY_PARAM);
            double price = Double.parseDouble(req.getParameter(PRICE_PARAM));
            int quantity = Integer.parseInt(req.getParameter(QUANTITY_PARAM));
            Part part = req.getPart(FILE_PARAM);
            String pathToPicture = Picture.PICTURE.upload(part, req);
            pathToPicture = Picture.PICTURE.fromServerToDB(pathToPicture);
            Date addingDate = new Date();
            Product product = new Product(name, description, category, price, quantity, addingDate, pathToPicture);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            ProductService productService = serviceFactory.getProductService();
            if (productService.add(product)) {
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
