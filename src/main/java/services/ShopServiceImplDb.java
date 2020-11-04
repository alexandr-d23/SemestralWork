package services;

import entities.*;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ShopServiceImplDb implements ShopService{

    @Override
    public <K extends ShopBooster> Answer buyBooster(int userId, int itemId) throws SQLException {
        UserService<User> service = new UserServiceImplDb<>();
        User user = service.getUser(userId);
        ShopBooster item = getBoosterById(itemId);
        if(user.getScore().compareTo(BigInteger.valueOf(item.getCost()))<0){
            return new Answer(false, "Недостаточно денег");
        }
        else{
            user.setScore(user.getScore().subtract(BigInteger.valueOf(item.getCost())));
            user.addBooster(item);
            service.addBooster(user, item);
            return new Answer(true);
        }
    }

    private <K extends ShopBooster> ShopBooster getBoosterById(int boosterId) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products where id = ?;");
        statement.setInt(1, boosterId);
        ResultSet set = statement.executeQuery();
        if(set.next()){
            return getBoosterFromSet(set,connection);
        }
        return null;
    }


    private ShopBooster getBoosterFromSet(ResultSet set, Connection connection) throws SQLException {
        int id = set.getInt("id");
        BoostTypes type = BoostTypes.valueOf(set.getString("item_type"));
        int cost = set.getInt("cost");
        int value = set.getInt("value");
        String description = set.getString("description");
        return new Booster(id,cost,type,value,description,id);
    }

    @Override
    public <K extends ShopBooster> Collection<K> getItems() throws SQLException {
        Collection<K> collection = new ArrayList<>();
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products;");
        ResultSet set = statement.executeQuery();
        while (set.next()){
            collection.add((K)getBoosterFromSet(set,connection));
        }
        return collection;
    }

    @Override
    public <K extends ShopBooster> ShopBooster getItemById(int itemId) throws SQLException {
        Collection<K> collection = new ArrayList<>();
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM products where id = ?;");
        statement.setInt(1,itemId);
        ResultSet set = statement.executeQuery();
        if(set.next()){
            return (K)getBoosterFromSet(set,connection);
        }
        return null;
    }

}
