package com.ontheroad.mysql.socketUtil;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageDecoder extends CumulativeProtocolDecoder {  
    @Override  
    protected boolean doDecode(IoSession session, IoBuffer in,ProtocolDecoderOutput out) throws Exception {  
    	CharsetDecoder charsetDecoder = Charset.defaultCharset().newDecoder();
    	String raw=in.getString(charsetDecoder);
    	Pattern pattern = Pattern.compile("(?<=LDCT)(\\d{2})(\\d{12}):(\\w+),(\\d{3})([,\\-\\+\\w\\.+]*"
    			+ "),(\\w{2})(?=>)");
    	Matcher matcher = pattern.matcher(raw);
    	if(matcher.find()) {
    		out.write(raw);
    		return true;
    	}else{
    		in.mark();//标记当前位置，以便reset 
    		in.reset();   
    		return false;
    	}

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
    		stringBuffer.append((char) b[i]); //可以根据需要自己改变类型      
    	}      
    	return stringBuffer.toString();      
    }  
}