package servlets;

import entities.User;
import entities.UserParametersService;
import services.ValidatorService;
import services.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;


public class RegistrationServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        UserParametersService parameters = ServiceLocator.getUserParametersService();
        ValidatorService validator = ServiceLocator.getRegValidatorService();
        Map<String,String> userFields = parameters.getValuesFromRequest(request);
        try {
            Answer answer = validator.validate(userFields);
            if (answer.isSuccessful()) {
                UserService<User> service = ServiceLocator.getUserService();
                service.addUser(new User(userFields.get("email"), userFields.get("name"), userFields.get("password"), userFields.get("gender"), userFields.get("country")));
                request.setAttribute("registered", "true");
            }
            else{
                request.setAttribute("registered", "false");
                request.setAttribute("failedDescription", answer.getDescription());
            }
        }
        catch (SQLException e) {
                request.setAttribute("registered","false");
                request.setAttribute("failedDescription", "На данный момент регистрация невозможна, попробуйте позже");
        }
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request, response);
    }
}
