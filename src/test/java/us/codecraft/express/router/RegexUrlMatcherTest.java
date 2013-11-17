package us.codecraft.express.router;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author code4crafter@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class RegexUrlMatcherTest {

	@Test
	public void testRegexUrlPatternCompile() {
		RegexUrlMatcher regexUrlMatcher = RegexUrlMatcher.compile("/${id}/${op}");
		assertEquals(2, regexUrlMatcher.getPathVariables().size());
		assertEquals("id", regexUrlMatcher.getPathVariables().get(0));
		assertEquals("op", regexUrlMatcher.getPathVariables().get(1));
	}

	@Test
	public void testUrlMatch() {
        RegexUrlMatcher regexUrlMatcher = RegexUrlMatcher.compile("/${id}/${op}");
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getRequestURI()).thenReturn("/12/start");
        boolean match = regexUrlMatcher.match(mockRequest);
        assertEquals(true, match);
        when(mockRequest.getRequestURI()).thenReturn("/12start");
        match = regexUrlMatcher.match(mockRequest);
        assertEquals(false, match);
    }
}
