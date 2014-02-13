package us.codecraft.express.connector.netty;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import us.codecraft.express.WebServer;
import us.codecraft.express.connector.AbstractWebServer;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * @author code4crafter@gmail.com
 */
public class NettyWebServer extends AbstractWebServer {

    private ServerBootstrap bootstrap;

    @Override
    public WebServer stop() throws Exception {
        bootstrap.shutdown();
        return this;
    }

    @Override
    public WebServer start() throws Exception {
        bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Set up the event pipeline factory.
        bootstrap.setPipelineFactory(new HttpServerPipelineFactory(getUrlRouter()));

        bootstrap.setOption("child.tcpNoDelay", true);

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(getPort()));
        return this;
    }
}
