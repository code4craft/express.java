package us.codecraft.express.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author code4crafter@gmail.com
 */
public abstract class ContextAwareController implements Controller {

	private ThreadLocal<HttpServletRequest> requests;

	private ThreadLocal<HttpServletResponse> responses;

	protected abstract void doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		requests.set(request);
		responses.set(response);
		doExecute(request, response);
		requests.remove();
		responses.remove();
	}

    protected  HttpServletRequest getRequest(){
        return requests.get();
    }

    protected HttpServletResponse getResponse(){
        return responses.get();
    }
}
