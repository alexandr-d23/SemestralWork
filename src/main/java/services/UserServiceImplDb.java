package services;

import entities.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserServiceImplDb<T extends User> implements UserService<T> {

    @Override
    public T getUser(String email) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where email = ?;");
        statement.setString(1, email);
        ResultSet set = statement.executeQuery();
        if(set.next()){
            return getUserFromSet(set,connection);
        }
        return null;
    }

    private Collection<ShopBooster> getBoosters(Connection connection, int userId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM user_purchases where user_id = ?;");
        Collection<ShopBooster> collection = new ArrayList<>();
        statement.setInt(1,userId);
        ResultSet boosterSet = statement.executeQuery();
        while (boosterSet.next()){
            int cost = boosterSet.getInt("cost");
            int id = boosterSet.getInt("id");
            BoostTypes type = BoostTypes.valueOf(boosterSet.getString("item_type"));
            int value = boosterSet.getInt("value");
            int product_id = boosterSet.getInt("product_id");
            String description = boosterSet.getString("description");
            collection.add(new Booster(id,cost,type,value,description,product_id));
        }
        return collection;
    }

    @Override
    public Collection<T> getUsers() throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users ;");
        ArrayList<T> list = new ArrayList<>();
        ResultSet set = statement.executeQuery();
        while(set.next()){
            list.add(getUserFromSet(set,connection));
        }
        return list;
    }

    @Override
    public Collection<T> getTopUsers() throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users ORDER BY  score DESC LIMIT 10;");
        ArrayList<T> list = new ArrayList<>();
        ResultSet set = statement.executeQuery();
        while(set.next()){
            list.add(getUserFromSet(set,connection));
        }
        return list;
    }

    @Override
    public T getUser(int id) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM users where id = ?;");
        statement.setInt(1, id);
        ResultSet set = statement.executeQuery();
        if(set.next()){
            return getUserFromSet(set,connection);
        }
        return null;
    }

    private T getUserFromSet(ResultSet set, Connection connection) throws SQLException {
        int userID = set.getInt("id");
        String userEmail = set.getString("email");
        String userName = set.getString("name");
        String userPassword = set.getString("password");
        String gender = set.getString("gender");
        String country = set.getString("country");
        BigInteger score = set.getBigDecimal("score").toBigInteger();
        boolean isAdmin = set.getBoolean("role");
        BigInteger clickPower = set.getBigDecimal("clickPower").toBigInteger();
        Collection<ShopBooster> collection = getBoosters(connection,userID);
        T user = (T)(new User(userID,userEmail,userName,userPassword,gender,country,score,clickPower,collection));
        user.setRole(isAdmin);
        return user;
    }

    @Override
    public void updateUser(T updatedUser)throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user_purchases (cost, value, description, item_type, user_id)  VALUES(?,?,?,?,?);");
        int i = 1;
        statement = connection.prepareStatement("UPDATE users SET email = ?, name = ?, gender = ?, country = ?, clickpower = ?, score = ? WHERE id = ?");
        statement.setString(i++,updatedUser.getEmail());
        statement.setString(i++,updatedUser.getName());
        statement.setString(i++,updatedUser.getGender());
        statement.setString(i++,updatedUser.getCountry());
        statement.setBigDecimal(i++, new BigDecimal(updatedUser.getClickPower()));
        statement.setBigDecimal(i++, new BigDecimal(updatedUser.getScore()));
        statement.executeUpdate();
    }

    @Override
    public void addScore(int id,int value) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE users SET  score = score + clickpower*? where id = ?;");
        statement.setInt(1,value);
        statement.setInt(2,id);
        statement.executeUpdate();
    }


    @Override
    public <K extends ShopBooster> void addBooster(T updatedUser, K item)throws SQLException{
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO user_purchases (cost, value, description, item_type, user_id, product_id)  VALUES(?,?,?,?,?,?);");
        int i = 1;
        statement.setInt(i++,item.getCost());
        statement.setInt(i++,item.getValue());
        statement.setString(i++,item.getDescription());
        statement.setString(i++,item.getBoostType().name());
        statement.setInt(i++, updatedUser.getId());
        statement.setInt(i,item.getId());
        statement.executeUpdate();
        statement = connection.prepareStatement("UPDATE users SET clickpower = ?, score = ? WHERE id = ?");
        statement.setBigDecimal(1,  new BigDecimal(updatedUser.getClickPower()));
        statement.setBigDecimal(2, new BigDecimal(updatedUser.getScore()));
        statement.setInt(3,updatedUser.getId());
        statement.executeUpdate();
    }

    @Override
    public void deleteUser(int userID) throws SQLException {
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?");
        statement.setInt(1,userID);
        statement.executeUpdate();
    }

    @Override
    public void addUser(T user) throws SQLException{
        Connection connection = DBConnector.getConnection();
        addUser(connection,user);
    }

    @Override
    public void addUsers(Collection<T> users) throws SQLException{
        Connection connection = DBConnector.getConnection();
        PreparedStatement statement;
        for(User user : users) {
            addUser(connection,user);
        }
    }

    private void addUser(Connection connection, User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into users (email,name,password,gender,country,score,clickpower)  values (?,?,?,?,?,?,?);");
        int i = 1;
        statement.setString(i++,user.getEmail());
        statement.setString(i++,user.getName());
        statement.setString(i++,user.getPassword());
        statement.setString(i++,user.getGender());
        statement.setString(i++,user.getCountry());
        statement.setBigDecimal(i++,new BigDecimal(user.getScore()));
        statement.setBigDecimal(i, new BigDecimal(user.getClickPower()));
        statement.executeUpdate();
    }
}
