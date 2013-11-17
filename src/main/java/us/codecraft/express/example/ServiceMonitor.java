package us.codecraft.express.example;

import us.codecraft.express.WebServer;
import us.codecraft.express.controller.AjaxController;
import us.codecraft.express.controller.ParamMap;
import us.codecraft.express.controller.ResultMap;

/**
 * @author code4crafter@gmail.com
 */
public class ServiceMonitor {

	private int count;

	private WebServer webServer;

	public ServiceMonitor(WebServer webServer) {
		this.webServer = webServer;
        monitor();
	}

	private void monitor() {
		webServer.get("/service/count", new AjaxController() {
			@Override
			public Object ajax(ParamMap params) {
				return ResultMap.create().put("count", count);
			}
		});
	}

	public static void main(String[] args) throws Exception {
		WebServer server = WebServer.jettyServer().port(8080);
		ServiceMonitor serviceMonitor = new ServiceMonitor(server);
		server.start();
		for (int i = 0; i < 1000; i++) {
            serviceMonitor.count = i;
            Thread.sleep(1000);
		}
	}
}
