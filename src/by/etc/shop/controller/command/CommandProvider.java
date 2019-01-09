package by.etc.shop.controller.command;

import by.etc.shop.controller.command.admin.product.AddProduct;
import by.etc.shop.controller.command.admin.product.ChangeProduct;
import by.etc.shop.controller.command.admin.product.DeleteProduct;
import by.etc.shop.controller.command.admin.stock.AddStock;
import by.etc.shop.controller.command.admin.stock.ChangeStock;
import by.etc.shop.controller.command.admin.stock.DeleteStock;
import by.etc.shop.controller.command.common.SignOut;
import by.etc.shop.controller.command.language.ChangeLanguage;
import by.etc.shop.controller.command.common.Registration;
import by.etc.shop.controller.command.common.SignIn;
import by.etc.shop.controller.command.user.AddToBasket;
import by.etc.shop.controller.command.user.ChangeQuantity;
import by.etc.shop.controller.command.user.DeleteFromBasket;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
   public CommandProvider(){

       repository.put(CommandName.SIGNIN, new SignIn());
       repository.put(CommandName.REGISTRATION, new Registration());
       repository.put(CommandName.SIGNOUT, new SignOut());
       repository.put(CommandName.CHANGELANGUAGE, new ChangeLanguage());
       repository.put(CommandName.ADDPRODUCT, new AddProduct());
       repository.put(CommandName.DELETEPRODUCT, new DeleteProduct());
       repository.put(CommandName.CHANGEPRODUCT, new ChangeProduct());
       repository.put(CommandName.ADDSTOCK, new AddStock());
       repository.put(CommandName.DELETESTOCK, new DeleteStock());
       repository.put(CommandName.CHANGESTOCK, new ChangeStock());
       repository.put(CommandName.ADDTOBASKET, new AddToBasket());
       repository.put(CommandName.DELETEFROMBASKET, new DeleteFromBasket());
       repository.put(CommandName.CHANGEQUANTITY, new ChangeQuantity());
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

