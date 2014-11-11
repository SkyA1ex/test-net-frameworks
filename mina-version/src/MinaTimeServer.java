import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaTimeServer {

	public static final int PORT = 8089;
	
	public static void main(String[] args) throws IOException {
		
		IoAcceptor acceptor = new NioSocketAcceptor();
		// Добавляем фильтры
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(
				new TextLineCodecFactory( Charset.forName("UTF-8"))));
		// Handler, который управляет соединениями и запрашивает текущее время
		acceptor.setHandler(new TimeServerHandler());
		acceptor.getSessionConfig().setReadBufferSize( 2048 );
		// Указываем, что делать когда сессия простаивает 
		// И время, после которого сессия считается простаиваемой
		acceptor.getSessionConfig().setIdleTime( IdleStatus.BOTH_IDLE, 10 );
		// Связывает acceptor с указанным портом
		acceptor.bind(new InetSocketAddress(PORT));
	}

}
