package servlets;

import entities.Booster;
import entities.ShopBooster;
import entities.User;
import entities.UserParametersService;
import services.Answer;
import services.ServiceLocator;
import services.UserService;
import services.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = ServiceLocator.getUserService().getUser((Integer)req.getSession().getAttribute("id"));
            req.setAttribute("email",user.getEmail());
            req.setAttribute("clickPower",user.getClickPower());
            req.setAttribute("country",user.getCountry());
            req.setAttribute("purchases",user.getBoosters());
            req.setAttribute("gender", user.getGender());
            req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
        }
        catch (SQLException throwable) {
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserParametersService parameters = ServiceLocator.getUserParametersService();
        ValidatorService validator = ServiceLocator.getRegValidatorService();
        Map<String,String> userFields = parameters.getValuesFromRequest(request);
        try {
            Answer answer = validator.validate(userFields);
            if (answer.isSuccessful()) {
                UserService<User> service = ServiceLocator.getUserService();
                User user = new User(userFields.get("email"), userFields.get("name"), userFields.get("password"), userFields.get("gender"), userFields.get("country"));
                user.setId((Integer)request.getSession().getAttribute("id"));
                service.updateUser(user);
                request.setAttribute("changed", "true");
            }
            else{
                request.setAttribute("changed", "false");
                request.setAttribute("failedDescription", answer.getDescription());
            }
        }
        catch (SQLException e) {
            request.setAttribute("changed","false");
            request.setAttribute("failedDescription", "На данный момент смена данных невозможна, попробуйте позже");
        }
        request.getRequestDispatcher("/WEB-INF/views/registration.jsp").forward(request,response);


    }
}
