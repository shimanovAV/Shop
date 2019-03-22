package by.etc.shop.controller.command.user.like;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Like;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import by.etc.shop.service.like.LikeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLike implements Command {

    public static final String PAGE_PARAM = "page";
    public static final String PRODUCT_ID_PARAM = "productId";
    public static final String USER_ID_PARAM = "userId";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            Like like;
            String page = req.getParameter(PAGE_PARAM);
            int productId = Integer.parseInt(req.getParameter(PRODUCT_ID_PARAM));
            String userId = req.getParameter(USER_ID_PARAM);
            like = new Like(productId, userId);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LikeService likeService = serviceFactory.getLikeService();
            if (likeService.changeLike(like)) {
                Catalog.CATALOG.putLikeIn(req.getSession());
                resp.sendRedirect(page);
            } else {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }

}
