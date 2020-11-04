package services;

import entities.User;
import entities.UserParametersService;
import entities.ValidatedUserParameters;

public class ServiceLocator {

    public static <T extends User> UserService<T> getUserService(){
        return new UserServiceImplDb<>();
    }

    public static<T extends User> GameService<T> getGameService(){
        return new GameServiceImpl<>();
    }

    public static<T extends User> ShopService getShopService(){
        return new ShopServiceImplDb();
    }

    public static ValidatorService getRegValidatorService(){
        return new ValidatorServiceImpl();
    }

    public static ValidatorService getAuthValidatorService(){
        return new ValidatorServiceImplAuth();
    }
    
    public static UserParametersService getUserParametersService(){
        return new ValidatedUserParameters();
    }

    public static AchievementService getAchievementService(){
        return new AchievementServiceImplDb();
    }

}
