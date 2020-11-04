package services;

import entities.User;

import java.sql.SQLException;
import java.util.Map;

public class ValidatorServiceImpl implements ValidatorService {
    private final StringBuilder description = new StringBuilder();
    @Override
    public Answer validate(Map<String,String> params) throws SQLException {
        String email = params.get("email");
        if(checkRegex(params)){
            UserService<User> service = ServiceLocator.getUserService();
            if(service.getUser(email)!=null){
                return new Answer(false, "Пользователь с таким email уже существует");
            }
            return new Answer(true);
        }
        else {
            return new Answer(false, description.toString());
        }
    }

    public boolean checkRegex(Map<String,String> params){
        String email = params.get("email");
        String password = params.get("password");
        String name = params.get("name");
        boolean corrected = true;
        if(email == null || !email.matches("^[A-Za-z\\-_.0-9]{2,20}@[A-Za-z]{2,10}\\.[a-z]{2,5}$")){
            description.append("Неверный формат ввода email . <br>");
            corrected = false;
        }
        if(name == null || name.length()< 4){
            description.append("В имени должно быть не менее 4 символов.<br>");
            corrected = false;
        }
        if(password == null || password.length()<8){
            description.append("Пароль должен содержать не менее 8 символов.<br>");
            corrected = false;
        }
        return corrected;
    }
}
