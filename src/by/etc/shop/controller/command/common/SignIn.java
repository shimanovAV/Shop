package by.etc.shop.controller.command.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.Product;
import by.etc.shop.entity.User;
import by.etc.shop.service.user.UserService;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

public class SignIn implements Command {


    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PAGE = "page";
    public static final String USER = "user";
    public static final String ERROR = "error";
    public static final String ADMIN_PAGE = "/Admin";

    public SignIn() {
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        HttpSession session = req.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService clientService = serviceFactory.getClientService();
        String page=null;
        try {
            User user = clientService.singIn(login, password);
            if (user != null) {
                session.setAttribute(USER, user);
                Catalog.CATALOG.putLikeIn(session);
                Catalog.CATALOG.putOrderIn(session);
                if(user.isAccessLevel()){
                    page=ADMIN_PAGE;
                    RequestDispatcher dispatcher = req.getRequestDispatcher(page);
                    if (dispatcher != null) {
                        dispatcher.forward(req, resp);
                    }
                } else{
                    page=req.getParameter(PAGE);
                    page = page.substring(0, page.length()-1);
                    resp.sendRedirect(page);
                }
            } else {
                page=req.getParameter(PAGE);
                session.setAttribute(ERROR, true);
            }

        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}


