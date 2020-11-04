package entities;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class User {
    private int id;
    private String email;
    private String name;
    private String password;
    private String gender;
    private String country;
    private boolean role;
    private BigInteger score;
    private BigInteger clickPower;
    private Collection<ShopBooster> collection;
    private User(){ }

    public User(String email, String name, String password, String gender, String country) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.score = BigInteger.valueOf(0);
        this.clickPower = BigInteger.ONE;
        collection = new ArrayList<>();
    }

    public User(int id, String email, String name, String password, String gender, String country, BigInteger score, BigInteger clickPower, Collection<ShopBooster> collection) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.score = score;
        this.clickPower = clickPower;
        this.collection = collection;
    }

    public Collection<ShopBooster> getBoosters() {
        return collection;
    }

    public void activateBoosters(){
        clickPower = BigInteger.ONE;
        for(ShopBooster b : collection){
            if(b.getBoostType().equals(BoostTypes.mult))clickPower = b.boost(clickPower);
        }
        for(ShopBooster b : collection){
            if(b.getBoostType().equals(BoostTypes.add))clickPower = b.boost(clickPower);
        }
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public void addBooster(ShopBooster booster){
        collection.add(booster);
        activateBoosters();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getClickPower() {
        return clickPower;
    }

    public void setClickPower(BigInteger clickPower) {
        this.clickPower = clickPower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(gender, user.gender) &&
                Objects.equals(country, user.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, gender, country);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public BigInteger getScore() {
        return score;
    }

    public void setScore(BigInteger score) {
        this.score = score;
    }


    public void setCountry(String country) {
        this.country = country;
    }
}
