package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

public class CAPTCHAText extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession(true);

        char data[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9'};
        char index[]=new char[6];

        Random r = new Random();
        for(int i=0;i<(index.length);i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }

        String captcha = String.valueOf(index);
        session.setAttribute("CAPTCHA", captcha);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(captcha);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {}
}

