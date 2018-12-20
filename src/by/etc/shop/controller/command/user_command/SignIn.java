package by.etc.shop.controller.command.user_command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.service.ClientService;
import by.etc.shop.service.ServiceException;
import by.etc.shop.service.ServiceFactory;
import java.io.IOException;

public class SignIn implements Command {

    public static final String URL_TO_HOME_PAGE = "/homepage";
    public static final String URL_TO_ERROR_PAGE = "/errorpage";

    public SignIn() {
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String responce = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();
        try {
            if(clientService.singIn(login, password)){
                responce = URL_TO_HOME_PAGE;
            }
            else{
                responce = URL_TO_ERROR_PAGE; //переделать на запись выплывающую
            }

        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return responce;
    }
}


