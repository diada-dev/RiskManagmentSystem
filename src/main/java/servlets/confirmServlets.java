package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import RMS.*;
import java.util.Random;

public class confirmServlets extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        final long time = System.currentTimeMillis();
        final HttpSession session = request.getSession(false);

        String message = request.getParameter("sha_result");

        response.setContentType("text/html;charset=utf-8");

        if (message == null || message.isEmpty()) {
            session.setAttribute("FirstConfirmSHA", time);

            response.setStatus(HttpServletResponse.SC_OK);

            try {
                Random r = new Random();
                int ran = 500 + r.nextInt(1000);
                session.setAttribute("SHAVal", ran);

                String val = null;
                val = getHash.toHexString(getHash.getSHA(Integer.toString(ran)));
                session.setAttribute("SHAVal", val);
                response.getWriter().print(val);

            } catch (NoSuchAlgorithmException e) {
            }

        } else {
            if (message.equals(session.getAttribute("SHAVal"))) {
                long t = time - (long)session.getAttribute("FirstConfirmSHA");

                RiskManagmentSystem RMS = new RiskManagmentSystem();
                SyslogServer serv = new SyslogServer();

                String inn = session.getAttribute("INN").toString();
                serv.info("Verify client OnLoad for transaction of " + inn);
                float i = RMS.getInterval(inn);

                long s = (long)session.getAttribute("SecondTimeCaptcha") - (long)session.getAttribute("FirstTimeCaptcha");
                if ((t>=s-i)&&(t<=s+i)){
                    serv.info("Success client transaction of " + inn);
                    response.getWriter().print("true");
                } else {response.getWriter().print("false");}
            } else { response.getWriter().print("false");};

        }
    }
}

