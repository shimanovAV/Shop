package by.etc.shop.controller.command.common.authorization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.Catalog;
import by.etc.shop.entity.User;
import by.etc.shop.service.user.UserService;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;

import java.io.IOException;

public class SignIn implements Command {


    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String PAGE = "page";
    public static final String USER = "user";
    public static final String ADMIN_PAGE = "/Admin";
    public static final int BEGIN_INDEX = 0;
    public static final int LAST_SYMBOL = 1;
    private static final String ERROR = "error";

    public SignIn() {
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page;
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        HttpSession session = req.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService clientService = serviceFactory.getClientService();
        try {
            User user = clientService.singIn(login, password);
            if (user != null ) {
                session.setAttribute(ERROR, false);
                session.setAttribute(USER, user);
                if (user.isAccessLevel()) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher(ADMIN_PAGE);
                    if (dispatcher != null) {
                        dispatcher.forward(req, resp);
                    }
                } else {
                    Catalog.CATALOG.putLikeIn(session);
                    Catalog.CATALOG.putOrderIn(session);
                    page = req.getParameter(PAGE);
                    page = page.substring(BEGIN_INDEX, page.length() - LAST_SYMBOL);
                    resp.sendRedirect(page);
                }
            } else {
                session.setAttribute(ERROR, true);
                page = req.getParameter(PAGE);
                page = page.substring(BEGIN_INDEX, page.length() - LAST_SYMBOL);
                resp.sendRedirect(page);
            }

        } catch (ServiceException | IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}


