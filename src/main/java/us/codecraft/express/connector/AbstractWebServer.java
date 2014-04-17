package us.codecraft.express.connector;

import us.codecraft.express.WebServer;
import us.codecraft.express.controller.AjaxController;
import us.codecraft.express.controller.Controller;
import us.codecraft.express.router.UrlRouter;

/**
 * @author code4crafter@gmail.com
 */
public abstract class AbstractWebServer extends WebServer {

    private UrlRouter urlRouter = new UrlRouter();

    private int port;

    @Override
    public WebServer port(int port) {
        this.port = port;
        return this;
    }

    @Override
    public WebServer get(String url, Controller controller) {
        urlRouter.addController(url, controller, "get");
        return this;
    }

    @Override
    public WebServer post(String url, Controller controller) {
        urlRouter.addController(url, controller, "post");
        return this;
    }

    @Override
    public WebServer get(String url, AjaxController controller) {
        return get(url, (Controller) controller);
    }

    @Override
    public WebServer post(String url, AjaxController controller) {
        return post(url, (Controller) controller);
    }

    protected int getPort() {
        return port;
    }

    protected UrlRouter getUrlRouter() {
        return urlRouter;
    }
}
