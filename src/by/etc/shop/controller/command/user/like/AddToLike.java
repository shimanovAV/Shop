package by.etc.shop.controller.command.user.like;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.user.basket.BasketProduct;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Like;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;
import by.etc.shop.service.like.LikeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddToLike implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Like like;
            String page = req.getParameter("page");
            int productId = Integer.parseInt(req.getParameter("productId"));
            String userId = req.getParameter("userId");
            like = new Like(productId, userId);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LikeService likeService = serviceFactory.getLikeService();
            if(likeService.addToLike(like)){
                RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                Catalog.CATALOG.putIn(req.getSession());
                if (dispatcher != null) {
                    dispatcher.forward(req, resp);
                }
            }
            else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }

}
