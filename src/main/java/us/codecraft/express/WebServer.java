package us.codecraft.express;

import us.codecraft.express.connector.jetty.JettyWebServer;
import us.codecraft.express.controller.Controller;

/**
 * @author code4crafter@gmail.com
 */
public abstract class WebServer {

	public abstract WebServer bind(int port);

	public abstract WebServer get(String url, Controller controller);

	public abstract WebServer post(String url, Controller controller);

    public abstract WebServer stop();

    public abstract WebServer start();

    public static WebServer jettyServer(){
        return new JettyWebServer();
    }

}
