package services;

import entities.User;

import java.sql.SQLException;
import java.util.Map;

public class ValidatorServiceImplAuth implements ValidatorService {

    @Override
    public Answer validate(Map<String, String> params) throws SQLException {
        String email = params.get("email");
        String password = params.get("password");
        UserService<User> service = ServiceLocator.getUserService();
        User user = service.getUser(email);
        if(user !=null){
            if(user.getPassword().equals(password)){
                return new Answer(true);
            }
            else{
                return new Answer(false,"Неверный пароль");
            }
        }
        else {
            return new Answer(false, "Пользователь с таким email не найден");
        }
    }
}
