package servlets;

import entities.User;
import services.GameService;
import org.json.JSONObject;
import services.ServiceLocator;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/WEB-INF/views/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService<User> service = ServiceLocator.getUserService();
        GameService<User> game = ServiceLocator.getGameService();
        int value = Integer.parseInt(req.getParameter("value"));
        int id = Integer.parseInt(req.getSession().getAttribute("id").toString());
        try {
            User user = service.getUser(id);
            game.click(id,value);
            System.out.println("Пришёл запрос на " + value);
            req.getSession().setAttribute("score",user.getScore());
            resp.setContentType("application/json");
            JSONObject obj = new JSONObject();
            obj.put("clickPower",user.getClickPower());
            resp.getWriter().print(obj.toString());
        } catch (SQLException throwable) {
            req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
        }

    }
}
