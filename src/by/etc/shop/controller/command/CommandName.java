package by.etc.shop.controller.command;

public enum CommandName {
    SIGNIN,
    REGISTRATION,
    SIGNOUT,

    CHANGELANGUAGE,

    ADDPRODUCT,
    DELETEPRODUCT,
    CHANGEPRODUCT,

    FINDPRODUCTBYID,
    FINDPRODUCT,

    ADDSTOCK,
    CHANGESTOCK,
    DELETESTOCK,

    ADDTOBASKET,
    CHANGEQUANTITY,
    DELETEFROMBASKET,
    BASKETPRODUCT,

    CANCELORDER,
    MAKEORDER,
    SENDORDER,
    ORDERPRODUCT,

    CHANGELIKE,
    LIKEPRODUCT,

    NEWPRODUCTS,
    SALEPRODUCTS;
}
