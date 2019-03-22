package by.etc.shop.service.like;

import by.etc.shop.entity.Like;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;

public interface LikeService {
    boolean changeLike(Like like) throws ServiceException;

    List<Product> getAllFromLikes(String userLogin) throws ServiceException;
}

