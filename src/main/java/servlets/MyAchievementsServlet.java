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

@WebServlet("/myAchievements")
public class MyAchievementsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AchievementService achievementService = ServiceLocator.getAchievementService();
            achievementService.updateUserAchievements((Integer) req.getSession().getAttribute("id"));
            achievementService.<Achievement>getAchievements();
            req.setAttribute("achievements",achievementService.getUserAchievements((Integer) req.getSession().getAttribute("id")));
            req.getRequestDispatcher("/WEB-INF/views/my_achievements.jsp").forward(req,resp);
        }
        catch (Exception e){
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }
    }
}
