package by.etc.shop.controller.command.common.catalog;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SaleProduct implements Command {
    public static final String SALE_PRODUCTS = "/ProductOnSales";
    public static final String CATALOG_PARAM = "catalog";
    public static final String SALE_PRODUCTS_ATTRIBUTE = "saleProducts";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            HttpSession session = req.getSession();
            List<Product> allProducts = (List<Product>) session.getAttribute(CATALOG_PARAM);
            List<Product> saleProducts = allProducts.stream()
                    .filter(p -> p.isOnSale()).collect(Collectors.toList());
            session.setAttribute(SALE_PRODUCTS_ATTRIBUTE, saleProducts);
            RequestDispatcher dispatcher = req.getRequestDispatcher(SALE_PRODUCTS);
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServletException | IOException e) {
            throw new CommandException(e);
        }
    }
}

