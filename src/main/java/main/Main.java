package main;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.PayFormServlet;
//import servlets.CaptchaServlet;
import servlets.VerifyServlet;
import servlets.CAPTCHAText;

public class Main {
    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        //******************************************************************************
        PayFormServlet requestServlet = new PayFormServlet();
        context.addServlet(new ServletHolder(requestServlet), "/payform");

        //CaptchaServlet requestCaptchaServlet = new CaptchaServlet();
        //context.addServlet(new ServletHolder(requestCaptchaServlet), "/captcha");

        VerifyServlet requestVerifyServlet = new VerifyServlet();
        context.addServlet(new ServletHolder(requestVerifyServlet), "/verify");

        CAPTCHAText requestCaptchaServlet = new CAPTCHAText();
        context.addServlet(new ServletHolder(requestCaptchaServlet), "/text-c");
        //******************************************************************************
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(80);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
