package com.ontheroad.mysql.socketUtil;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDecoder extends CumulativeProtocolDecoder {  
	private final static Logger logger=LoggerFactory.getLogger(MessageDecoder.class);
    @Override  
    protected boolean doDecode(IoSession session, IoBuffer in,ProtocolDecoderOutput out) throws Exception {  
    	logger.info("----------------消息进入MessageDecoder--------------------------------------");
    	CharsetDecoder charsetDecoder = Charset.defaultCharset().newDecoder();
    	String raw=in.getString(charsetDecoder);
    	logger.info("MessageDecoder==================raw"+raw);
    	if(raw.contains("<")&&raw.contains(">")){
    		logger.info("命令符合要求"+raw);
    		return true;
    	}
    	logger.info("命令不符合要求，处理一次"+raw);
    	return false;
    	/*
    	if(in.remaining() < 4){//正常当长度小于4的时候说明断包了  
    		logger.info("----------------处理一次断包-----------in.remaining() < 4---------------------------");
    		return false;  
    	}else{  
    		in.mark();//标记当前位置  
    		byte[]  bs = new byte[4];  
    		in.get(bs);  
    		int len = byteArrayToInt(bs);//调用方法将byte数组转换为int  
    		if(len > in.remaining()){  
    			in.reset();//消息不够，断包处理  
    			logger.info("----------------处理一次断包-------------消息不够，断包处理-------------------------");
    			return false;  
    		}else{  
    			byte[] bytes = new byte[len];  
    			in.get(bytes,0,len);  
    			//创建一个包含一个完整数据包的IoBuffer对象  
    			IoBuffer buffer = IoBuffer.wrap(bs);  
    			buffer.put(bytes);  
    			//将IoBuffer对象写出，在IoHandlerAdapter类的messageReceived方法中进行处理  
    			out.write(buffer);  
    		}  
    		if(in.remaining() > 0){//如果还粘了包，就让父类在进行一次处理  
    			return true;  
    		}  
    		return false;//处理成功，让父类进行接收下一个包  
    	}  */
    } 
    //byte 数组与 int 的相互转换 
    public  int byteArrayToInt(byte[] b) { 
    	return b[3] & 0xFF | 
    			(b[2] & 0xFF) << 8 | 
    			(b[1] & 0xFF) << 16 | 
    			(b[0] & 0xFF) << 24; 
    } 
    public static String ioBufferToString(IoBuffer iobuffer){      
    	System.out.println("message = " + iobuffer + iobuffer.limit());      
    	iobuffer.flip();    //调换buffer当前位置，并将当前位置设置成0      
    	byte[] b = new byte[iobuffer.limit()];      
    	iobuffer.get(b);      
    	//此处用stringbuffer是因为　String类是字符串常量，是不可更改的常量。而StringBuffer是字符串变量，它的对象是可以扩充和修改的。       
    	StringBuffer stringBuffer = new StringBuffer();      

    	for(int i = 0; i < b.length; i++){      
    		System.out.println("====" + b[i]);      
    		stringBuffer.append((char) b[i]); //可以根据需要自己改变类型      
    		System.out.println(b[i] +"---------" +i);      
    	}      
    	return stringBuffer.toString();      
    }  
}