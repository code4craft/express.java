package us.codecraft.express.example;

import us.codecraft.express.WebServer;
import us.codecraft.express.controller.AjaxController;
import us.codecraft.express.controller.ParamMap;
import us.codecraft.express.controller.ResultMap;

/**
 * @author code4crafter@gmail.com
 */
public class RestAjax {

	public static void main(String[] args) {
		try {
			WebServer.jettyServer().get("/", new AjaxController() {
                @Override
                public Object ajax(ParamMap params) {
                    return ResultMap.create().put("code", 200).put("msg", "ok").put("detail", "测试一下中文");
                }
            }).get("/echo", new AjaxController() {
                @Override
                public Object ajax(ParamMap params) {
                    return params;
                }
            }).port(8080).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
