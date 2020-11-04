package servlets;

import entities.Achievement;
import services.AchievementService;
import services.ServiceLocator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


@WebServlet("/achievements")
public class AchievementsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            AchievementService service = ServiceLocator.getAchievementService();
            Collection<Achievement> collection = service.getAchievementsWithProgress(Integer.parseInt(req.getSession().getAttribute("id").toString()));
            req.setAttribute("achievements",collection);
            req.getRequestDispatcher("/WEB-INF/views/achievements.jsp").forward(req,resp);
        } catch (SQLException throwable) {
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }


    }

}
