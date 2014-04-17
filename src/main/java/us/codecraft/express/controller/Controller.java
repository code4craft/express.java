package us.codecraft.express.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author code4crafter@gmail.com
 */
@FunctionalInterface
public interface Controller {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
