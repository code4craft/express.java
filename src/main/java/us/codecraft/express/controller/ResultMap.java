package us.codecraft.express.controller;

import java.util.HashMap;

/**
 * @author code4crafter@gmail.com
 */
public class ResultMap extends HashMap<String,Object> {

    public static ResultMap create(){
        return new ResultMap();
    }

    @Override
    public ResultMap put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
