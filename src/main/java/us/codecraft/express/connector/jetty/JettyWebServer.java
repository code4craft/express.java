package us.codecraft.express.connector.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import us.codecraft.express.WebServer;
import us.codecraft.express.connector.AbstractWebServer;
import us.codecraft.express.router.UrlRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author code4crafter@gmail.com
 */
public class JettyWebServer extends AbstractWebServer {

	private Server server;

	@Override
	public WebServer stop() throws Exception {
		if (server != null) {
			server.stop();
		}
		return this;
	}

	@Override
	public WebServer start() throws Exception {
		server = new Server(getPort());
		server.setHandler(new WebServerHandler(getUrlRouter()));
		server.start();
		return this;
	}

	static class WebServerHandler extends AbstractHandler {

        private UrlRouter urlRouter;

        WebServerHandler(UrlRouter urlRouter) {
            this.urlRouter = urlRouter;
        }

        @Override
		public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
				throws IOException, ServletException {
            try {
                urlRouter.route(request).execute(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
