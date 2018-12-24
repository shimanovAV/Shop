package by.etc.shop.controller.command;

import by.etc.shop.controller.command.admin_command.product.AddProduct;
import by.etc.shop.controller.command.admin_command.product.ChangeProduct;
import by.etc.shop.controller.command.admin_command.product.DeleteProduct;
import by.etc.shop.controller.command.language.ChangeLanguage;
import by.etc.shop.controller.command.user_command.Registration;
import by.etc.shop.controller.command.user_command.SignIn;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
   public CommandProvider(){

       repository.put(CommandName.SIGNIN, new SignIn());
       repository.put(CommandName.REGISTRATION, new Registration());
       repository.put(CommandName.CHANGELANGUAGE, new ChangeLanguage());
       repository.put(CommandName.ADDPRODUCT, new AddProduct());
       repository.put(CommandName.DELETEPRODUCT, new DeleteProduct());
       repository.put(CommandName.CHANGEPRODUCT, new ChangeProduct());
    }
    public Command getCommand(String name) throws CommandException{
        CommandName commandName =null;
        Command command = null;
        try{
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        }catch(IllegalArgumentException | NullPointerException e){
            throw new CommandException(e);
        }
        return command;
    }

}

