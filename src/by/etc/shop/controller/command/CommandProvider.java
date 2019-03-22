package by.etc.shop.controller.command;

import by.etc.shop.controller.command.admin.order.SendOrder;
import by.etc.shop.controller.command.admin.product.AddProduct;
import by.etc.shop.controller.command.admin.product.ChangeProduct;
import by.etc.shop.controller.command.admin.product.DeleteProduct;
import by.etc.shop.controller.command.admin.stock.AddStock;
import by.etc.shop.controller.command.admin.stock.ChangeStock;
import by.etc.shop.controller.command.admin.stock.DeleteStock;
import by.etc.shop.controller.command.admin.order.OrderProduct;
import by.etc.shop.controller.command.common.authorization.Registration;
import by.etc.shop.controller.command.common.authorization.SignIn;
import by.etc.shop.controller.command.common.authorization.SignOut;
import by.etc.shop.controller.command.common.catalog.NewProducts;
import by.etc.shop.controller.command.common.catalog.SaleProduct;
import by.etc.shop.controller.command.common.finding.FindProduct;
import by.etc.shop.controller.command.common.finding.FindProductById;
import by.etc.shop.controller.command.language.ChangeLanguage;
import by.etc.shop.controller.command.user.basket.AddToBasket;
import by.etc.shop.controller.command.user.basket.BasketProduct;
import by.etc.shop.controller.command.user.basket.ChangeQuantity;
import by.etc.shop.controller.command.user.basket.DeleteFromBasket;
import by.etc.shop.controller.command.user.like.ChangeLike;
import by.etc.shop.controller.command.user.like.LikeProduct;
import by.etc.shop.controller.command.user.order.CancelOrder;
import by.etc.shop.controller.command.user.order.MakeOrder;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();

    public CommandProvider() {

        repository.put(CommandName.SIGNIN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Registration());
        repository.put(CommandName.SIGNOUT, new SignOut());
        repository.put(CommandName.CHANGELANGUAGE, new ChangeLanguage());
        repository.put(CommandName.ADDPRODUCT, new AddProduct());
        repository.put(CommandName.DELETEPRODUCT, new DeleteProduct());
        repository.put(CommandName.FINDPRODUCTBYID, new FindProductById());
        repository.put(CommandName.CHANGEPRODUCT, new ChangeProduct());
        repository.put(CommandName.ADDSTOCK, new AddStock());
        repository.put(CommandName.DELETESTOCK, new DeleteStock());
        repository.put(CommandName.CHANGESTOCK, new ChangeStock());
        repository.put(CommandName.ADDTOBASKET, new AddToBasket());
        repository.put(CommandName.DELETEFROMBASKET, new DeleteFromBasket());
        repository.put(CommandName.CHANGEQUANTITY, new ChangeQuantity());
        repository.put(CommandName.BASKETPRODUCT, new BasketProduct());
        repository.put(CommandName.CHANGELIKE, new ChangeLike());
        repository.put(CommandName.LIKEPRODUCT, new LikeProduct());
        repository.put(CommandName.FINDPRODUCT, new FindProduct());
        repository.put(CommandName.CANCELORDER, new CancelOrder());
        repository.put(CommandName.MAKEORDER, new MakeOrder());
        repository.put(CommandName.SENDORDER, new SendOrder());
        repository.put(CommandName.ORDERPRODUCT, new OrderProduct());
        repository.put(CommandName.NEWPRODUCTS, new NewProducts());
        repository.put(CommandName.SALEPRODUCTS, new SaleProduct());

    }

    public Command getCommand(String name) throws CommandException {
        CommandName commandName;
        Command command;
        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CommandException(e);
        }
        return command;
    }

}

