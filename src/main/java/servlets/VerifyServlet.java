package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        final long time = System.currentTimeMillis();
        final HttpSession session = request.getSession(false);

        String message = request.getParameter("captcha");
        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }


        String val = session.getAttribute("CAPTCHA").toString();

        if (message.equals(val)){
            response.getWriter().print("true");
            session.setAttribute("FirstTimeCaptcha", time);}
        else
            response.getWriter().print("false");

    }
}
