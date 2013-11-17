package us.codecraft.express.controller;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import us.codecraft.express.router.RegexUrlMatcher;

import javax.servlet.http.HttpServletResponse;

/**
 * @author code4crafter@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class AjaxControllerTest {

	@Test
	public void test() throws Exception {
        RegexUrlMatcher regexUrlMatcher = RegexUrlMatcher.compile("/${id}/${op}");
        MockHttpServletRequest mockRequest = new MockHttpServletRequest();
        mockRequest.setRequestURI("/12/start");
        boolean match = regexUrlMatcher.match(mockRequest);
        Assert.assertTrue(match);
        HttpServletResponse mockResponse = new MockHttpServletResponse();
        AjaxController ajaxController = new AjaxController() {
            @Override
            public Object ajax(ParamMap params) {
                Assert.assertEquals(12, params.getInt("id"));
                Assert.assertEquals("start", params.get("op"));
                return params;
            }
        };
		ajaxController.execute(mockRequest, mockResponse);
	}
}
