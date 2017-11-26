package com.ontheroad.mysql.socketUtil;

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
    	if(in.remaining() < 4){//正常当长度小于4的时候说明断包了  
            return false;  
        }else{  
            in.mark();//标记当前位置  
            byte[]  bs = new byte[4];  
            in.get(bs);  
            int len = byteArrayToInt(bs);//调用方法将byte数组转换为int  
            if(len > in.remaining()){  
                in.reset();//消息不够，断包处理  
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
        }  
    } 
  //byte 数组与 int 的相互转换 
    public  int byteArrayToInt(byte[] b) { 
     return b[3] & 0xFF | 
      (b[2] & 0xFF) << 8 | 
      (b[1] & 0xFF) << 16 | 
      (b[0] & 0xFF) << 24; 
    } 
}