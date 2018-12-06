package by.etc.shop.controller.command;

public class CommandException extends Exception {
    private static final long serialVersionUID = 2L;
    public CommandException(){super();
    }
    public CommandException(String message){
        super(message);
    }
    public CommandException(Exception e){
        super(e);
    }
    public CommandException(String message, Exception e){
        super(message, e);
    }
}
