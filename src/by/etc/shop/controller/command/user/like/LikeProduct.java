package by.etc.shop.controller.command.user.like;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.User;
import by.etc.shop.service.like.LikeService;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class LikeProduct implements Command {
    public static final String LIKE_PAGE = "/LikePage";
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        try {
            User user = (User)req.getSession().getAttribute("user");
            String userLogin = user.getLogin();
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            LikeService likeService = serviceFactory.getLikeService();
            List<Product> likes = likeService.getAllFromLikes(userLogin);
            if(likes!=null){
                req.setAttribute("likes", likes);
                RequestDispatcher dispatcher = req.getRequestDispatcher(LIKE_PAGE);
                if(dispatcher!=null){
                    dispatcher.forward(req, resp);
                }
            }
            else{
                HttpSession session = req.getSession();
                session.setAttribute("error", true);
                resp.sendRedirect("anotherPage");
            }
        } catch (ServiceException| ServletException | IOException e){
            throw new CommandException(e);
        }
    }
}
