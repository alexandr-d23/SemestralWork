package servlets;

import entities.User;
import entities.UserParametersService;
import services.ValidatorService;
import services.ServiceLocator;
import services.Answer;
import services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        ValidatorService validator = ServiceLocator.getAuthValidatorService();
        UserParametersService params = ServiceLocator.getUserParametersService();
        Map<String,String> fields = params.getValuesFromRequest(request);
        try {
            Answer answer = validator.validate(fields);
            if(answer.isSuccessful()) {
                UserService<User> reader = ServiceLocator.getUserService();
                User user = reader.getUser(fields.get("email"));
                if(user.isRole()){
                    request.getSession().setAttribute("admin",true);
                }
                request.getSession().setAttribute("id",user.getId());
                request.getSession().setAttribute("authorized","true");
                request.getSession().setAttribute("name",user.getName());
                request.getSession().setAttribute("score",user.getScore());
                response.sendRedirect(request.getContextPath());
            }
            else {
                request.getSession().setAttribute("authorized","false");
                request.setAttribute("failedDescription", answer.getDescription());
                request.getRequestDispatcher("/WEB-INF/views/authorization.jsp").forward(request,response);
            }
        } catch (SQLException throwable) {
            request.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
            request.getRequestDispatcher("/WEB-INF/views/authorization.jsp").forward(request, response);
    }
}
