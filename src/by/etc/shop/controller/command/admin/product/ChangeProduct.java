package by.etc.shop.controller.command.admin.product;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.admin.Picture;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.Stock;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.product.ProductService;
import by.etc.shop.service.stock.StockService;

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

public class ChangeProduct implements Command {
    public static final String ADMIN_PAGE = "/Admin";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {

            Product product;
            String page = req.getParameter("page");
            int id = Integer.parseInt(req.getParameter("productID"));
            try {
                    String name = req.getParameter("name");
                    String description = req.getParameter("description");
                    String category = req.getParameter("category");
                    double oldPrice = Double.parseDouble(req.getParameter("price"));
                    int quantity = Integer.parseInt(req.getParameter("quantity"));
                    Date addingDate = new Date();
                    String pathToPicture = req.getParameter("productPathToPicture");
                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                StockService stockService = serviceFactory.getStockService();
                    String stockName = req.getParameter("stock");
                    double newPrice = stockService.recalculation(stockName, oldPrice);
                    product = new Product(id, name, description, category, newPrice, quantity, addingDate,
                            stockName, oldPrice, pathToPicture);
                    ProductService productService = serviceFactory.getProductService();
                    if (productService.update(product)) {
                        Catalog.CATALOG.putIn(req.getSession());
                        RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_PAGE);
                        if (dispatcher != null) {
                            dispatcher.forward(req, resp);
                        }
                    } else {
                        HttpSession session = req.getSession();
                        session.setAttribute("error", true);
                        resp.sendRedirect("anotherPage");
                    }

            }catch (ServiceException | IOException | ServletException e) {
                throw new CommandException(e);
            }
    }
}
