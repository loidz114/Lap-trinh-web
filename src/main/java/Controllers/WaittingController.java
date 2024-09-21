package Controllers;

import Models.UserMoles;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/waiting")

public class WaittingController  extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        HttpSession session= req.getSession();
        if(session != null && session.getAttribute("account") != null) {
            UserMoles u=(UserMoles) session.getAttribute("account");
            req.setAttribute("username", u.getUsername());
            if(u.getId()==1) {
                resp.sendRedirect(req.getContextPath()+"/admin");
            }else if(u.getId()==2) {
                resp.sendRedirect(req.getContextPath()+"/home");
            }else {
                resp.sendRedirect(req.getContextPath()+"/teacher");
            }
        }else {
            resp.sendRedirect(req.getContextPath()+"/login");
        }}}

