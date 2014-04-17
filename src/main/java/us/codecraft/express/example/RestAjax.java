package us.codecraft.express.example;

import us.codecraft.express.WebServer;
import us.codecraft.express.controller.ResultMap;

/**
 * @author code4crafter@gmail.com
 */
public class RestAjax {

    public static void main(String[] args) throws Exception {
        WebServer.jettyServer().get("/", (params) -> {
            return ResultMap.create().put("code", 200).put("msg", "ok");
        }).get("/echo", (params) -> {
            return params;
        }).get("/echo/${id}", (params) -> {
            return ResultMap.create().put("id", params.getInt("id"));
        }).port(8080).start();
    }
}
