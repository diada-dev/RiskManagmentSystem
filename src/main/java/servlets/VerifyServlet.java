package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import RMS.*;


public class VerifyServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {}

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        final long time = System.currentTimeMillis();
        final HttpSession session = request.getSession(false);

        String message = request.getParameter("captcha");
        String message1 = request.getParameter("SHA256");

        response.setContentType("text/html;charset=utf-8");

        if (!(message == null || message.isEmpty())) {
            response.setStatus(HttpServletResponse.SC_OK);

            String val = session.getAttribute("CAPTCHA").toString();

            if (message.equals(val)) {
                response.getWriter().print("true");
                session.setAttribute("FirstTimeCaptcha", time);
                String inn = request.getParameter("inn").toString();
                session.setAttribute("INN", inn);
            } else {
                response.getWriter().print("false");
            }

            return;
        }

        if (!(message1 == null || message1.isEmpty())) {
            response.setStatus(HttpServletResponse.SC_OK);

            String val = null;
            try {
                val = getHash.toHexString(getHash.getSHA(session.getAttribute("CAPTCHA").toString()));
            } catch (NoSuchAlgorithmException e) {
                val = "";
            }

            if (message1.equals(val)){

                session.setAttribute("SecondTimeCaptcha", time);}

                //проверка вычисления хэша прошла успешно - можно отправлять на антифрод
                RiskManagmentSystem RMS = new RiskManagmentSystem();
                SyslogServer serv = new SyslogServer();

                String inn = session.getAttribute("INN").toString();
                serv.info("Start transaction for " + inn);
                float r = RMS.getRisks(inn);
                if (r==1){
                    //ablolute risk, start honeypot to wait attackers error
                    usingHoneyPot hp = new usingHoneyPot();
                    session.setAttribute("HoneyPot", hp);
                    response.getWriter().print("false"); //вот тут вопрос - нужно ли держать атакующего на связи
                    //или просто сказать ему не получилось не фартануло
                } else {
                   callAntifraud af = new callAntifraud();
                   session.setAttribute("AntiFraud", af);
                   response.getWriter().print("true");
                }

            } else {
            response.getWriter().print("false");
            }

    }
}
