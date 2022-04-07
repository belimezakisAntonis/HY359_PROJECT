package servlets;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Set-Cookie", "key=value; HttpOnly; SameSite=strict");
        HttpSession session=request.getSession(true);
        if(session.getAttribute("loggedIn")!=null){
            session.invalidate();

            response.setStatus(200);
        }
        else{
            response.setStatus(403);
        }
    }
}
