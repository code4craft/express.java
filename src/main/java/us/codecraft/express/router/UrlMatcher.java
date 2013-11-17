package us.codecraft.express.router;

import javax.servlet.http.HttpServletRequest;

/**
 * @author code4crafter@gmail.com
 */
public abstract class UrlMatcher {

	public abstract boolean match(HttpServletRequest request);

	public static UrlMatcher compile(String url) {
		return RegexUrlMatcher.compile(url);
	}

}
