package by.etc.shop.controller;

import by.etc.shop.controller.command.Command;
import by.etc.shop.controller.command.CommandProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.etc.shop.controller.command.CommandException;

import java.io.IOException;

public class Controller extends HttpServlet {

    public static final String SIGN_IN = "SignIn";
    public static final String REGISTRATION = "Registration";
    public static final String URL_TO_ERROR_PAGE = "/errorpage";
    public static final String COMMAND = "Command";

    public Controller() {
        super();
    }

    private final CommandProvider provider = new CommandProvider();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(COMMAND);
        try{
            Command command = provider.getCommand(commandName);
           command.execute(req, resp);
        } catch(CommandException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(COMMAND);
        try{
        Command command = provider.getCommand(commandName);
           command.execute(req, resp);
        } catch(CommandException e){
            e.printStackTrace();
        }
    }
}
