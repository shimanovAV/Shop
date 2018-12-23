package by.etc.shop.controller.command.language;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;
import by.etc.shop.controller.listener.CurrentLanguage;
import by.etc.shop.controller.listener.SessionListener;
import by.etc.shop.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ChangeLanguage implements Command {

    private static final String LANGUAGE = "language";
    private static final String PAGE = "page";
    public static final String URL_TO_ERROR_PAGE = "/errorpage";

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        String responce = null;
        HttpSession session = req.getSession();
        String language = req.getParameter(LANGUAGE);
        CurrentLanguage.LANGUAGE.setLanguage(language);
        session.setAttribute(LANGUAGE, language);
        String page = req.getParameter(PAGE);
        try{
        if(page != null) {
            resp.sendRedirect(page);
        } else {
            responce = URL_TO_ERROR_PAGE;
        }} catch (IOException e){
            throw new CommandException(e);
        }
    return responce;
    }
}
