package us.codecraft.express.router;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author code4crafter@gmail.com
 */
public class RegexUrlMatcher extends UrlMatcher {

	private Pattern pattern;

	private List<String> pathVariables;

    private static Pattern variablesPattern = Pattern.compile("\\$\\{(\\w+)\\}");

    public RegexUrlMatcher(Pattern pattern, List<String> pathVariables) {
        this.pattern = pattern;
        this.pathVariables = pathVariables;
    }

    @Override
	public boolean match(HttpServletRequest request) {
		Matcher matcher = pattern.matcher(request.getRequestURI());
		if (matcher.matches()) {
			if (pathVariables != null && pathVariables.size() > 0) {
				for (int i = 0; i < matcher.groupCount(); i++) {
					request.setAttribute(pathVariables.get(i), matcher.group(i));
				}
			}
			return true;
		}
		return false;
	}

    public static RegexUrlMatcher compile(String url){
        Matcher matcher = variablesPattern.matcher(url);
        StringBuffer accum = new StringBuffer();
        List<String> pathVariables = new ArrayList<String>();
        while (matcher.find()){
            pathVariables.add(matcher.group(1));
            matcher.appendReplacement(accum,"[^/]+");
        }
        matcher.appendTail(accum);
        return new RegexUrlMatcher(Pattern.compile(accum.toString()),pathVariables);
    }

    @Override
    public String toString() {
        return "RegexUrlMatcher{" +
                "pattern=" + pattern +
                ", pathVariables=" + pathVariables +
                '}';
    }

    public Pattern getPattern() {
        return pattern;
    }

    public List<String> getPathVariables() {
        return pathVariables;
    }
}
