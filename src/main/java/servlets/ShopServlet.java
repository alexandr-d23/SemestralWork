package servlets;

import entities.Booster;
import entities.User;
import services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/shop")
public class ShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object authorized = req.getSession().getAttribute("authorized");
        if(authorized == null || authorized.toString().equals("false")){
            resp.sendRedirect(req.getContextPath());
        }
        else{
            ShopService shopService = ServiceLocator.getShopService();
            Collection<Booster> items;
            try {
                items = shopService.getItems();
                req.setAttribute("items", items);
                req.getRequestDispatcher("/WEB-INF/views/shop.jsp").forward(req, resp);
            } catch (SQLException throwable) {
                req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("id")!=null) {
            int itemId = Integer.parseInt(req.getParameter("id"));
            req.removeAttribute("id");
            int userId = (Integer) req.getSession().getAttribute("id");
            UserService<User> service = ServiceLocator.getUserService();
            GameService<User> gameService = ServiceLocator.getGameService();
            ShopService shopService = ServiceLocator.getShopService();
            Collection<Booster> items = null;
            Answer answer;
            try {
                answer = gameService.buyBooster(userId, itemId);
                items = shopService.getItems();
                req.setAttribute("items", items);
                if (answer.isSuccessful()) {
                    User user = service.getUser(userId);
                    req.getSession().setAttribute("score", user.getScore());
                    req.setAttribute("bought", "Предмет успешно куплен");
                    resp.sendRedirect(req.getContextPath()+"/profile");
                } else {
                    req.setAttribute("no_suc", "true");
                    req.setAttribute("description", answer.getDescription());
                    req.getRequestDispatcher("/WEB-INF/views/shop.jsp").forward(req, resp);
                }
                ;
            } catch (SQLException throwable) {
                req.getRequestDispatcher("/WEB-INF/views/wrong.jsp").forward(req, resp);
            }
        }


    }
}
