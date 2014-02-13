package us.codecraft.express.connector.netty;

import org.jboss.netty.buffer.DynamicChannelBuffer;
import org.jboss.netty.channel.*;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import us.codecraft.express.router.UrlRouter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author yihua.huang@dianping.com
 */
public class HttpServerHandler extends SimpleChannelUpstreamHandler {

    private UrlRouter urlRouter;

    public HttpServerHandler(UrlRouter urlRouter) {
        this.urlRouter = urlRouter;
    }

    @Override
    public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        super.channelClosed(ctx, e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent event) throws Exception {
        if (event.getMessage() instanceof HttpRequest) {
            try {
                HttpServletRequest httpServletRequest = new NettyHttpServletRequestAdaptor((HttpRequest) event.getMessage(), ctx.getChannel());
                HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);
                response.setContent(new DynamicChannelBuffer(200));
                HttpServletResponse httpServletResponse = new NettyHttpServletResponseAdaptor(response, ctx.getChannel());
                urlRouter.route(httpServletRequest).execute(httpServletRequest, httpServletResponse);
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,response.getContent().writerIndex());
                ctx.getChannel().write(response);
                ctx.getChannel().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        //DO NOTHING ha ha!
//        super.exceptionCaught(ctx, e);
    }
}
