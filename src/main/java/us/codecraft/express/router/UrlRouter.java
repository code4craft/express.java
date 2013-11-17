package us.codecraft.express.router;

import us.codecraft.express.controller.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author code4crafter@gmail.com
 */
public class UrlRouter {

	private Map<String, MatcherAndController> getRouters = new LinkedHashMap<String, MatcherAndController>();

	private Map<String, MatcherAndController> postRouters = new LinkedHashMap<String, MatcherAndController>();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public void addController(String url, Controller controller, String method) {
		Lock writeLock = readWriteLock.writeLock();
		writeLock.lock();
		try {
			if ("get".equalsIgnoreCase(method)) {
				getRouters.put(url, new MatcherAndController(UrlMatcher.compile(url), controller));
			} else if ("post".equalsIgnoreCase(method)) {
				postRouters.put(url, new MatcherAndController(UrlMatcher.compile(url), controller));
			}
		} finally {
			writeLock.unlock();
		}
	}

	public Controller route(HttpServletRequest request) {
		Lock readLock = readWriteLock.readLock();
		readLock.lock();
		try {
            if ("get".equalsIgnoreCase(request.getMethod())){
                for (MatcherAndController matcherAndController : getRouters.values()) {
                    if (matcherAndController.getMatcher().match(request)){
                        return matcherAndController.getController();
                    }
                }
            } else if ("post".equalsIgnoreCase(request.getMethod())) {
                for (MatcherAndController matcherAndController : postRouters.values()) {
                    if (matcherAndController.getMatcher().match(request)){
                        return matcherAndController.getController();
                    }
                }
            }
		} finally {
			readLock.unlock();
		}
		return null;
	}

}
