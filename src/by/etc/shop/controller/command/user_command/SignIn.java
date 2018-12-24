package by.etc.shop.controller.command.user_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
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
    public static final String ERROR = "error";
    public static final String ACCESS_LEVEL = "accessLevel";

    public SignIn() {
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        HttpSession session = req.getSession();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService clientService = serviceFactory.getClientService();
        String page = req.getParameter(PAGE);
        try {
            User user = clientService.singIn(login, password);
            if (user != null && page != null) {
                session.setAttribute(USER, user);
            } else {
                session.setAttribute(ERROR, true);
            }
            resp.sendRedirect(page);
        } catch (ServiceException | IOException e) {
            throw new CommandException(e);
        }
    }
}


