package by.etc.shop.controller.command.common;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.entity.User;
import by.etc.shop.service.user.UserService;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Registration implements Command {
    public static final String PAGE = "page";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String BIRTHDAY = "birthday";
    public static final String LANGUAGE = "language";
    public static final String USER = "user";
    public static final String ERROR = "error";

    public Registration() {
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page = req.getParameter(PAGE);
        HttpSession session = req.getSession();
        String name = req.getParameter(NAME);
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        String dateString = req.getParameter(BIRTHDAY);
        try {
            Date birthday=new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            User user = new User(name, login, email, birthday);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService clientService = serviceFactory.getClientService();
            if (clientService.registration(user, password) && page != null) {
                session.setAttribute(USER, user);
            } else {
                session.setAttribute(ERROR, true);
            }
            resp.sendRedirect(page);
        } catch (ServiceException | ParseException | IOException e) {
            throw new CommandException(e);
        }
    }
}
