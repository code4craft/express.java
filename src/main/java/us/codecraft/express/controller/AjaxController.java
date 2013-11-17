package us.codecraft.express.controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
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
			// todo:support multi-params
			params.put(stringEntry.getKey(), stringEntry.getValue()[0]);
		}
		Enumeration<String> attributeNames = request.getAttributeNames();
		while (attributeNames.hasMoreElements()) {
			String key = attributeNames.nextElement();
            if (request.getAttribute(key) instanceof String){
                params.put(key, request.getAttribute(key).toString());
            }
		}
		Object result = ajax(params);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream(), "utf-8");
        outputStreamWriter.write(JSON.toJSONString(result));
        response.setStatus(200);
        outputStreamWriter.flush();
	}

	public abstract Object ajax(ParamMap params);
}
