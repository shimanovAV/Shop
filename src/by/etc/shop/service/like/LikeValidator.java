package by.etc.shop.service.like;

import by.etc.shop.entity.Like;

public class LikeValidator {
    public static boolean isValid(Like like){
        if(like.getProductId()<=0 ||
                like.getUserId()==null || like.getUserId().isEmpty()){
            return false;
        }
        return true;
    }
}
