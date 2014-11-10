import Netty.NettyServer;



public class ChatServer {
	
	public static final int port = 8089;
	
	public static void main(String[] args) throws Exception {
		
		new NettyServer(port).run();
		
	}
	
}
