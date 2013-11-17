package us.codecraft.express.controller;

import java.util.HashMap;

/**
 * @author code4crafter@gmail.com
 */
public class ParamMap extends HashMap<String, String> {

	public int getInt(String key) {
		String value = get(key);
		if (value == null) {
			return 0;
		}
		return Integer.parseInt(value);
	}

}
