package us.codecraft.express.router;

import us.codecraft.express.controller.Controller;

/**
 * @author code4crafter@gmail.com
 */
public class MatcherAndController {

    private final UrlMatcher matcher;

    private final Controller controller;

    public MatcherAndController(UrlMatcher matcher, Controller controller) {
        this.matcher = matcher;
        this.controller = controller;
    }

    public UrlMatcher getMatcher() {
        return matcher;
    }

    public Controller getController() {
        return controller;
    }
}
