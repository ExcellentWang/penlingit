package com.ontheroad.mysql.socketUtil;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class DeviceMessageCodecFactory implements ProtocolCodecFactory {

    private DeviceMessageLineDecoder decoder;
    private DeviceMessageLineEncoder encoder;

    public DeviceMessageCodecFactory() {
        decoder = new DeviceMessageLineDecoder();
        encoder = new DeviceMessageLineEncoder();
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }
}
