package by.etc.shop.controller.command;

import by.etc.shop.entity.User;
import by.etc.shop.service.ClientService;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Registration implements Command {
    public static final String URL_TO_HOME_PAGE = "/homepage";
    public static final String URL_TO_ERROR_PAGE = "/errorpage";
    public Registration() {
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String responce = null;
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        User user = new User(name, login, password, email);
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            if(clientService.registration(user)){
                responce = URL_TO_HOME_PAGE;
            } else{
                responce = URL_TO_ERROR_PAGE; //переделать на запись выплывающую
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return responce;
    }
}
