package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.stock.StockService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


public class ChangeProduct implements Command {
    public static final String ADMIN_PAGE = "/Admin";
    public static final String PRODUCT_ID_PARAM = "productID";
    public static final String NAME_PARAM = "name";
    public static final String DESCRIPTION_PARAM = "description";
    public static final String CATEGORY_PARAM = "category";
    public static final String PRICE_PARAM = "price";
    public static final String QUANTITY_PARAM = "quantity";
    public static final String PATH_TO_PICTURE = "productPathToPicture";
    public static final String STOCK_NAME_PARAM = "stock";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Product product;
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            StockService stockService = serviceFactory.getStockService();
            ProductService productService = serviceFactory.getProductService();

            int id = Integer.parseInt(req.getParameter(PRODUCT_ID_PARAM));
            String name = req.getParameter(NAME_PARAM);
            String description = req.getParameter(DESCRIPTION_PARAM);
            String category = req.getParameter(CATEGORY_PARAM);
            double oldPrice = Double.parseDouble(req.getParameter(PRICE_PARAM));
            int quantity = Integer.parseInt(req.getParameter(QUANTITY_PARAM));
            Date addingDate = new Date();
            String pathToPicture = req.getParameter(PATH_TO_PICTURE);
            String stockName = req.getParameter(STOCK_NAME_PARAM);
            double newPrice = stockService.recalculation(stockName, oldPrice);
            product = new Product(id, name, description, category, newPrice, quantity, addingDate,
                    stockName, oldPrice, pathToPicture);
            if (productService.update(product)) {
                Catalog.CATALOG.putIn(req.getSession());
                RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_PAGE);
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
