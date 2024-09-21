package Controllers;

import Models.UserMoles;
import Services.IUserService;
import Utils.Constant;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    IUserService service = new UserService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("Views/login.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isRememberMe = false;
        String remember = req.getParameter("remember");

        if("on".equals(remember)){
            isRememberMe = true;
        }
        String alertMsg="";
        if ( username.isEmpty() || password.isEmpty()){
            alertMsg = "Please fullfill password and username";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("Views/login.jsp").forward(req, resp);
            return;
        }
        UserMoles user = service.login(username, password);
        if(user!=null){
            HttpSession session = req.getSession(true);
            session.setAttribute("account", user);
            if(isRememberMe){
                saveRemeberMe(resp, username);
            }

            resp.sendRedirect(req.getContextPath()+"/waiting");
        }else{
            alertMsg ="Incorrect password or username";
            req.setAttribute("alert", alertMsg);
            req.getRequestDispatcher("Views/login.jsp").forward(req, resp);
        }

    }
    private void saveRemeberMe(HttpServletResponse response, String
            username){
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30*60);
        response.addCookie(cookie);
    }
}
