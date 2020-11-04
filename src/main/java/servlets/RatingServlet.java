package servlets;

import entities.User;
import services.ServiceLocator;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/rating")
public class RatingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService<User> users = ServiceLocator.getUserService();
        try {
            Collection<User> collection = users.getTopUsers();
            req.setAttribute("topUsers", collection);
            req.getRequestDispatcher("/WEB-INF/views/rating.jsp").forward(req,resp);
        } catch (SQLException throwable) {
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
