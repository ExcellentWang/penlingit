/*package com.ontheroad.mysql.ymodem;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class YModem {
	public final Logger logger=LoggerFactory.getLogger(YModem.class);
    private Modem modem;
    public YModem() {
        this.modem = new Modem();
    }
    
    *//**
     * 固件下发调用
     * @param file
     * @throws IOException
     *//*
    public void send(Path file,WriteFuture writeFuture,IoSession session ,String instructions) throws IOException {
    	  ByteBuffer byteBuffer=ByteBuffer.allocate(133);
        //check filename
        if (!file.getFileName().toString().matches("\\w{1,8}\\.\\w{1,3}")) {
            throw new IOException("Filename must be in DOS style (no spaces, max 8.3)");
        }

        //open file
        try (DataInputStream dataStream = new DataInputStream(Files.newInputStream(file))) {
            Timer timer = new Timer(Modem.WAIT_FOR_RECEIVER_TIMEOUT).start();
            logger.info("发送开始指令----------"+instructions);
//            session.write(instructions); //1.向设备发送信号，开启固件升级
            logger.info("------------------------1.向设备发送信号，开启固件升级-----------------");
//            boolean useCRC16 = modem.waitReceiverRequest(timer, writeFuture, session);//等待设备返回C
            CRC crc;
//            if (useCRC16){
            	crc = new CRC16();
//            }
//            else{
//            	crc = new CRC8();
//            }
            BasicFileAttributes readAttributes = Files.readAttributes(file, BasicFileAttributes.class);
            String fileNameString = file.getFileName().toString() + (char)0 + ((Long) Files.size(file)).toString()+" "+ Long.toOctalString(readAttributes.lastModifiedTime().toMillis() / 1000);
            byte[] fileNameBytes = Arrays.copyOf(fileNameString.getBytes(), 128);
            logger.info("------------------------开始发送第一个包（告诉设备文件信息）-----------------");
            modem.sendBlock(0, Arrays.copyOf(fileNameBytes, 128), 128, crc,writeFuture,session, byteBuffer);//2.发送第一个包（告诉设备文件信息）
//            modem.waitReceiverRequest(timer,writeFuture,session);//等待设备返回C
            //发送数据
            byte[] block = new byte[1024];
            modem.sendDataBlocks(dataStream, 1, crc, block,writeFuture,session, byteBuffer);
//            modem.sendEOT(writeFuture,session);
        }
    }

}
*/