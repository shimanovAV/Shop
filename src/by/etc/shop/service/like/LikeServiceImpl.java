package by.etc.shop.service.like;

import by.etc.shop.dao.DAOException;
import by.etc.shop.dao.DAOFactory;
import by.etc.shop.dao.like.LikeDAO;
import by.etc.shop.entity.Like;
import by.etc.shop.entity.Product;
import by.etc.shop.service.ServiceException;

import java.util.List;

public class LikeServiceImpl implements LikeService {

    public boolean changeLike(Like like) throws ServiceException {
        try {
            if (LikeValidator.isValid(like)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                LikeDAO likeDAO = daoObjectFactory.getLikeDAO();
                if (likeDAO.hasLike(like)) {
                    return likeDAO.delete(like);
                }
                return likeDAO.add(like);
            } else {
                throw new ServiceException("Like is not valid");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Product> getAllFromLikes(String userLogin) throws ServiceException {
        try {
            if (LikeValidator.isValidLogin(userLogin)) {
                DAOFactory daoObjectFactory = DAOFactory.getInstance();
                LikeDAO likeDAO = daoObjectFactory.getLikeDAO();
                return likeDAO.allProduct(userLogin);
            } else {
                throw new ServiceException("Like is not valid");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}
