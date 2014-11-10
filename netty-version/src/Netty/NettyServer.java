package Netty;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;



public class NettyServer {
	// Порт сервера
    private int port;
    // Группа для хранения всех созданных каналов
    public static DefaultChannelGroup allChannels  = new
			DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public NettyServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
    	
    	// boss принимает входящие соединения
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker обрабатывает траффик соединения, после того
        // как boss регистрирует соединение
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try {
        	// Вспомогательный класс для создания сервера
            ServerBootstrap b = new ServerBootstrap(); 
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // Каналы такого класса будут создаваться
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                	 // Добавляем обработчик канала
                     ch.pipeline().addLast(new ChatServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)   
             .childOption(ChannelOption.SO_KEEPALIVE, true);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync();
            
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
