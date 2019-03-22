package by.etc.shop.service.like;

import by.etc.shop.entity.Like;

public class LikeValidator {

    private static final int ZERO = 0;
    private static final String NULL = null;

    public static boolean isValid(Like like){
       return !(like.getProductId()<=ZERO ||
                like.getUserId()==NULL || like.getUserId().isEmpty());
    }

    public static boolean isValidLogin(String login) {
        return !(login == NULL || login.isEmpty());
    }
}
