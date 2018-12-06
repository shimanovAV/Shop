package by.etc.shop.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
   public CommandProvider(){

       repository.put(CommandName.SIGNIN, new SignIn());
       repository.put(CommandName.REGISTRATION, new Registration());
    }
    public Command getCommand(String name){
        CommandName commandName =null;
        Command command = null;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
//write log
        }
        return command;
    }

}

