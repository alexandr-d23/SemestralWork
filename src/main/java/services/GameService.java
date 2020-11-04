package services;

import entities.ShopBooster;
import entities.User;

import java.sql.SQLException;

public interface GameService<T extends User>  {
    
    void click(int userId,int value) throws SQLException;
    <K extends ShopBooster>Answer buyBooster(int userId, int itemId) throws SQLException;

}
