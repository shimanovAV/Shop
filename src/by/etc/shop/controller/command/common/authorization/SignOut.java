package by.etc.shop.controller.command.common.authorization;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignOut implements Command {
    public static final String PAGE = "/Start";

    public void execute(HttpServletRequest req, HttpServletResponse resp) throws CommandException {
        HttpSession session = req.getSession(false);
        try {
            session.invalidate();
            req.getRequestDispatcher(PAGE).forward(req, resp);
        } catch (IOException | ServletException e) {
            throw new CommandException(e);
        }
    }
}
