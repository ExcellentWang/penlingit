package com.ontheroad.mysql.socketUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeviceMessageLineDecoder implements ProtocolDecoder {
    @Override
    public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        int startPosition = in.position();
        while(in.hasRemaining()) {
            byte b = in.get();
            if(b == '>') {
                int currentPosition = in.position();
                int limit = in.limit();
                in.position(startPosition);
                in.limit(currentPosition);
                IoBuffer buf = in.slice();
                byte[] dest = new byte[buf.limit()];
                buf.get(dest);
                String str = new String(dest);
                out.write(str);
                in.position(currentPosition);
                in.limit(limit);
            }
        }
    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {

    }

    @Override
    public void dispose(IoSession session) throws Exception {

    }
}
