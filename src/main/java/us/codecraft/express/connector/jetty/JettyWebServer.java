package us.codecraft.express.connector.jetty;

import us.codecraft.express.controller.Controller;
import us.codecraft.express.WebServer;

/**
 * @author code4crafter@gmail.com
 */
public class JettyWebServer extends WebServer {
    @Override
    public WebServer bind(int port) {
        return null;
    }

    @Override
    public WebServer get(String url, Controller controller) {
        return null;
    }

    @Override
    public WebServer post(String url, Controller controller) {
        return null;
    }

    @Override
    public WebServer stop() {
        return null;
    }

    @Override
    public WebServer start() {
        return null;
    }
}
