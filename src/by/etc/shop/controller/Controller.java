package by.etc.shop.controller;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandProvider;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.etc.shop.controller.command.CommandException;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet({"/Relay"})
@MultipartConfig
public class Controller extends HttpServlet {

    private static Logger logger = LogManager.getLogger(Controller.class);

    public static final String COMMAND = "Command";

    public Controller() {
        super();
    }

    private final CommandProvider provider = new CommandProvider();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String commandName = req.getParameter(COMMAND);
        try {
            Command command = provider.getCommand(commandName);
            command.execute(req, resp);
        } catch (CommandException e) {
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String commandName = req.getParameter(COMMAND);
        try {
            Command command = provider.getCommand(commandName);
            command.execute(req, resp);
        } catch (CommandException e) {
            logger.error(e);
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
