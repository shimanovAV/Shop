package by.etc.shop.controller.command.common.authorization;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Registration implements Command {
    public static final String PAGE = "page";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String BIRTHDAY = "birthday";
    public static final String USER = "user";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final int BEGIN_INDEX = 0;
    public static final int LAST_SYMBOL = 1;
    private static final String ERROR = "error";

    public Registration() {
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String page = req.getParameter(PAGE);
        HttpSession session = req.getSession();
        Date birthday;
        String name = req.getParameter(NAME);
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String email = req.getParameter(EMAIL);
        String dateString = req.getParameter(BIRTHDAY);
        try {
            if(dateString.equals("")){
              birthday = new Date();
            } else {
                birthday = new SimpleDateFormat(DATE_FORMAT).parse(dateString);
            }
            User user = new User(name, login, email, birthday);
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService clientService = serviceFactory.getClientService();
            if(page != null) {
                if (clientService.registration(user, password)) {
                    session.setAttribute(USER, user);
                    session.setAttribute(ERROR, false);
                } else {
                    session.setAttribute(ERROR, true);
                }
                page = page.substring(BEGIN_INDEX, page.length() - LAST_SYMBOL);
                resp.sendRedirect(page);
            } else{
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (ServiceException | ParseException | IOException e) {
            throw new CommandException(e);
        }
    }
}
