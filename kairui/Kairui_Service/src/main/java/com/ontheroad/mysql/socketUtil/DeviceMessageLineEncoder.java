package com.ontheroad.mysql.socketUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class DeviceMessageLineEncoder implements ProtocolEncoder {
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        String str = null;
        str = message.toString();

        if(str != null) {
            CharsetEncoder charsetEncoder = (CharsetEncoder)session.getAttribute("encoder");
            if(charsetEncoder == null) {
                charsetEncoder = Charset.defaultCharset().newEncoder();
                session.setAttribute("encoder", charsetEncoder);
            }
            IoBuffer buf = IoBuffer.allocate(str.length());
            buf.setAutoExpand(true);
            buf.putString(str, charsetEncoder);
            buf.flip();
            out.write(buf);
        }
    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
