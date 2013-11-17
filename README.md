express.java
=====
>A tiny RESTful web framework with embed server. Used as instead of JMX for cross-language communication.

## Features:

* ### Convention over Configuration.
	
	No xml and no annotation. Just write an application by API in Java.
	
```java
WebServer.jettyServer().get("/", new AjaxController() {
    @Override
    public Object ajax(ParamMap params) {
        return ResultMap.create().put("code", 200);
    }
}).port(8080).start();
```

* ###

N
o xml and no 