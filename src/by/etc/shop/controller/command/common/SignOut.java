package by.etc.shop.controller.command.common;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOut implements Command {
    public static final String PAGE = "page";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession(false);
        String page = req.getParameter(PAGE);
        try {
            session.invalidate();
            resp.sendRedirect(page);
            //req.getRequestDispatcher(page).forward(req, resp);
        } catch (IOException e) {
            throw new CommandException(e);
        }
    }
}
