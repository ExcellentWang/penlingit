package com.ontheroad.util;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer implements ServletContextListener, HttpSessionListener  {

	private static final Logger logUtil = Logger.getLogger(MinaServer.class);

	private static NioSocketAcceptor acceptor;
	
	private static final int port = 8888;

	// 停止MINA服务
	public void contextDestroyed(ServletContextEvent sce) {
		try {
			MinaServer.acceptor.unbind();
			MinaServer.acceptor.dispose();
			logUtil.info("Mina服务停止...");
		} catch (Exception e) {
			logUtil.info(e);
		}
	}

	// 启动MINA服务
	public void contextInitialized(ServletContextEvent sce) {
		try {
			  // 创建服务器监听
			  acceptor = new NioSocketAcceptor();
			  acceptor.getFilterChain().addLast("logger", new LoggingFilter());
			  //这里不添加字符编码过滤器
			  acceptor.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new TextLineCodecFactory( Charset.forName( "UTF-8" ))));//指定编码过滤器  
			  // 指定业务逻辑处理器
			  acceptor.setHandler(new MinaServerHandler());
			  // 设置buffer的长度
			  acceptor.getSessionConfig().setReadBufferSize(2048);
			  // 设置连接超时时间
			  acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 300);
			  // 绑定端口
			  acceptor.bind(new InetSocketAddress(port));
			  logUtil.info("服务端启动成功...     端口号为：" + port);
		} catch (Exception e) {
			e.printStackTrace();
			logUtil.info("服务端启动异常....");
		}

	}

	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
	}

}
