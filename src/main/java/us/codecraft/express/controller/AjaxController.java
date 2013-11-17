package us.codecraft.express.controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author code4crafter@gmail.com
 */
public abstract class AjaxController extends ContextAwareController {

	@Override
	protected void doExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, String[]> parameterMap = request.getParameterMap();
		ParamMap params = new ParamMap();
		for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            //todo:support multi-params
			params.put(stringEntry.getKey(), stringEntry.getValue()[0]);
		}
		Object result = ajax(params);
		response.getOutputStream().print(JSON.toJSONString(result));
	}

	public abstract Object ajax(ParamMap params);
}
