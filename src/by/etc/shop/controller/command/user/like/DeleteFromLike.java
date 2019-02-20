package by.etc.shop.controller.command.user.like;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.command.user.basket.BasketProduct;
import by.etc.shop.entity.Basket;
import by.etc.shop.entity.Like;
import by.etc.shop.entity.User;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.basket.BasketService;
import by.etc.shop.service.like.LikeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteFromLike implements Command {

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Like like;
            HttpSession session = req.getSession();
            String page = req.getParameter("page");
            int productId = Integer.parseInt(req.getParameter("productID"));
            User user = (User)session.getAttribute("user");
            String userLogin = user.getLogin();
            like = new Like(productId, userLogin);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LikeService likeService = serviceFactory.getLikeService();

            if (likeService.deleteFromLike(like)) {
                LikeProduct likeProduct = new LikeProduct();
                likeProduct.execute(req,resp);
            } else {

                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }
}
