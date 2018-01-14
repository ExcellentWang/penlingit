package com.ontheroad.mysql.socketUtil;

import com.danga.MemCached.MemCachedClient;
import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.util.ConcurrentHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author wang 2018/1/10 上午10:19
 * @param
 * @return
 *  这是一个服务处理器,类似于controller 的做用.
**/

@Component("MinaServerHandler")
public class MinaServerHandler extends IoHandlerAdapter {

    private static final Logger logUtil = Logger.getLogger(MinaServerHandler.class);
    private String mas;

    @Autowired
    private DeviceMessageHandler handler;

    @Autowired
    private MemCachedClient memCachedClient;

    public static ConcurrentHashSet<IoSession> sessions = new ConcurrentHashSet<IoSession>();


    public MinaServerHandler() {
    }

    public MinaServerHandler(String mas) {
        this.mas = mas;
    }


    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logUtil.info("服务端与客户端创建连接...");
        Long time = System.currentTimeMillis();
        session.setAttribute("id", time);
        sessions.add(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logUtil.info("服务端与客户端连接打开...");
    }

    /**
     * @author wang 2018/1/10 下午12:04
     * @param
     * @return
     * 接收上报信息
     *
    **/
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String msg = message.toString();

        handler.recv(session, msg);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
//        logUtil.info("服务端发送信息成功...");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logUtil.info("服务端与客户端连接关闭...");
        session.close(true);
        sessions.remove(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        super.sessionIdle(session, status);
        logUtil.info("服务端进入空闲状态...");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        logUtil.info("服务端发送异常..." + cause);
        session.close(true);
    }
}
