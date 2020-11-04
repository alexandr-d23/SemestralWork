package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;


public class MyFilter implements Filter {
    private String[] forbidden = {"/achievements","/game","/myAchievements","/profile","/shop"};
    @Override
    public void doFilter(ServletRequest sReq, ServletResponse sResp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sReq;
        HttpServletResponse resp = (HttpServletResponse) sResp;
        String path = req.getRequestURI();
        path = path.substring(req.getContextPath().length());
        Object authorized = req.getSession().getAttribute("authorized");
        if(authorized == null || authorized.toString().equals("false")){
            for(String str : forbidden){
                if(path.equals(str)){
                    System.out.println(str);
                    System.out.println(path);
                    resp.sendRedirect(req.getContextPath());
                    return;
                }
            }
        }
        if(path.equals("/registration")) {
            if (authorized != null && authorized.toString().equals("true")) {
                System.out.println("Второй");
                System.out.println(path);
                resp.sendRedirect(req.getContextPath());
                return;
            }
        }

        if(path.equals("/admin")){
            Object admin = req.getSession().getAttribute("admin");
            if (admin == null || admin.toString().equals("false")) {
                resp.sendRedirect(req.getContextPath());
                return;
            }
        }
        filterChain.doFilter(req, resp);
    }

}
