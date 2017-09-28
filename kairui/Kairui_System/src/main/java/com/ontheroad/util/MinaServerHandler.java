package com.ontheroad.util;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;




public class MinaServerHandler extends IoHandlerAdapter {

	  private static final Logger logUtil = Logger.getLogger(MinaServerHandler.class);
	  
	  @Override
	  public void sessionCreated(IoSession session) throws Exception {
	    logUtil.info("服务端与客户端创建连接...");
	  }

	  @Override
	  public void sessionOpened(IoSession session) throws Exception {
	    logUtil.info("服务端与客户端连接打开...");
	  }

	  @Override
	  public void messageReceived(IoSession session, Object message) throws Exception {
	  	StringBuffer buffer = new StringBuffer();
		  System.out.println(message.toString());
	  	IoBuffer bbuf = (IoBuffer)message;
	  	byte[] byten = new byte[bbuf.limit()];
	  	bbuf.get(byten, bbuf.position(), bbuf.limit());

		String str = new String(byten,"utf-8");
		System.out.println(str);
//		try {
//			 session.write("123456789");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		session.write("123123");
	  }
	 

	  @Override
	  public void messageSent(IoSession session, Object message) throws Exception {
		  super.messageSent(session, message);
       System.out.println("服务端发送信息成功...");
	    logUtil.info("服务端发送信息成功...");
	  }

	  @Override
	  public void sessionClosed(IoSession session) throws Exception {
	    logUtil.info("服务端与客户端连接关闭...");
	    session.close(true);
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
	    logUtil.info("服务端发送异常..." + cause);
	    session.close(true);
	  }
	  
	  public static void main(String[] args) throws Exception{
		
		
	  }
	  
	  public static int byte2int(byte[] res) {   
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000   
		  
		int targets = (res[0] & 0xff) | ((res[1] << 8) & 0xff00) // | 表示安位或   
		| ((res[2] << 24) >>> 8) | (res[3] << 24);   
		return targets;   
		} 
	  
	  public static String getBinaryStrFromByteArr(byte[] bArr){  
	        String result ="";  
	        for(byte b:bArr ){  
	            result += getBinaryStrFromByte(b);  
	        }  
	        return result;    
	    } 
	  
	  /** 
	     * 把byte转化成2进制字符串 
	     * @param b 
	     * @return 
	     */  
	    public static String getBinaryStrFromByte(byte b){  
	        String result ="";  
	        byte a = b; ;  
	        for (int i = 0; i < 8; i++){  
	            byte c=a;  
	            a=(byte)(a>>1);//每移一位如同将10进制数除以2并去掉余数。  
	            a=(byte)(a<<1);  
	            if(a==c){  
	                result="0"+result;  
	            }else{  
	                result="1"+result;  
	            }  
	            a=(byte)(a>>1);  
	        }  
	        return result;  
	    }
	  

	}
