package servlets;


import entities.Achievement;
import entities.AchievementTypes;
import services.AchievementService;
import services.ServiceLocator;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AchievementService service = ServiceLocator.getAchievementService();
            Collection<Achievement> collection = service.getAchievements();
            req.setAttribute("achievements",collection);
            req.getRequestDispatcher("/WEB-INF/views/admin_achievements.jsp").forward(req,resp);
        } catch (SQLException throwable) {
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AchievementService achievementService = ServiceLocator.getAchievementService();
            int id = Integer.parseInt(req.getParameter("achievement-id"));
            AchievementTypes type = AchievementTypes.valueOf(req.getParameter("type"));
            String description = req.getParameter("description");
            int value = Integer.parseInt(req.getParameter("value"));
            if(req.getParameter("delete") !=null){
                achievementService.deleteAchievement(id);
            }
            else {
                Achievement achievement = new Achievement(id, type, value, description);
                if (req.getParameter("add") != null) {
                    achievementService.addAchievement(achievement);
                }
                else if(req.getParameter("change")!=null){
                    achievementService.updateAchievement(achievement);
                }
            }
        }
        catch (Exception e){
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath()+"/admin");
    }
}
