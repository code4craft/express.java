package us.codecraft.express.example;

import us.codecraft.express.WebServer;
import us.codecraft.express.controller.AjaxController;
import us.codecraft.express.controller.ParamMap;
import us.codecraft.express.controller.ResultMap;

/**
 * @author code4crafter@gmail.com
 */
public class RestJson {

	public static void main(String[] args) {
		WebServer.jettyServer().get("/", new AjaxController() {
			@Override
			public Object ajax(ParamMap params) {
				return ResultMap.create().put("code", 200);
			}
		}).bind(8080).start();
	}
}
