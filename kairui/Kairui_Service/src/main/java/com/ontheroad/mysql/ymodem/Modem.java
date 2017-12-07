package com.ontheroad.mysql.ymodem;


import java.io.*;
import java.nio.ByteBuffer;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

class Modem {

    /* Protocol characters used */
    protected static  byte SOH = 0x01; /* Start Of Header */
    protected static final byte STX = 0x02; /* Start Of Text (used like SOH but means 1024 block size) */
    protected static final byte EOT = 0x04; /* End Of Transmission */
    protected static final byte ACK = 0x06; /* ACKnowlege */
    protected static final byte NAK = 0x15; /* Negative AcKnowlege */
    protected static final byte CAN = 0x18; /* CANcel character */

    protected static final byte CPMEOF = 0x1A;
    protected static final byte ST_C = 'C';

    protected static final int MAXERRORS = 10;

    protected static final int BLOCK_TIMEOUT = 1000;
    protected static final int REQUEST_TIMEOUT = 3000;
    protected static final int WAIT_FOR_RECEIVER_TIMEOUT = 60_000;
    protected static final int SEND_BLOCK_TIMEOUT = 10_000;

    private  ByteBuffer byteBuffer=ByteBuffer.allocate(133);

    protected Modem() {
    	super();
    }


   /**
    * 判断设备返回的数据是不是C
    * @param timer
    * @return
    * @throws IOException
    */
    protected boolean waitReceiverRequest(Timer timer,WriteFuture writeFuture,IoSession session) throws IOException {
        int character;
        while (true) {
            try {
                character = readByte(timer, writeFuture, session);
                if (character == NAK)
                    return false;
                if (character == ST_C) {
                    return true;
                }
            } catch (TimeoutException e) {
                throw new IOException("Timeout waiting for receiver");
            }
        }
    }
    /**
     * 循环发送数据包
     * @param dataStream
     * @param blockNumber
     * @param crc
     * @param block
     * @param writeFuture
     * @param session
     * @throws IOException
     */
    protected void sendDataBlocks(DataInputStream dataStream, int blockNumber, CRC crc, byte[] block,WriteFuture writeFuture,IoSession session) throws IOException {
        int dataLength;
        while ((dataLength = dataStream.read(block)) != -1) {
            sendBlock(blockNumber++, block, dataLength, crc, writeFuture, session);
        }
    }
   
    /**
     * 发送一个单包
     * @param blockNumber 第几个包
     * @param block 数据包
     * @param dataLength 数据长度
     * @param crc crc校验方式
     * @throws IOException
     */
    protected void sendBlock(int blockNumber, byte[] block, int dataLength, CRC crc,WriteFuture writeFuture,IoSession session) throws IOException {
        int errorCount;
        int character;
        Timer timer = new Timer(SEND_BLOCK_TIMEOUT);
        if (dataLength < block.length) {
            block[dataLength] = CPMEOF;//补码操作
        }
        errorCount = 0;
        while (errorCount < MAXERRORS) {
        	timer.start();
        	byteBuffer.put(STX);//包头
        	byteBuffer.putInt(blockNumber);//序号
        	byteBuffer.putInt(~blockNumber);//反码
        	byteBuffer.put(block);//数据包
        	writeCRC(block, crc);//crc校验
        	writeFuture=session.write(byteBuffer.array());//mina发送数据
        	byteBuffer.clear();//发送之后清空缓存区
            while (true) {
                try {
                    character = readByte(timer, writeFuture, session);
                    if (character == ACK) {
                        return;
                    } else if (character == NAK) {
                        errorCount++;
                        break;
                    } else if (character == CAN) {
                        throw new IOException("Transmission terminated");
                    }
                } catch (TimeoutException e) {
                    errorCount++;
                    break;
                }
            }

        }

        throw new IOException("Too many errors caught, abandoning transfer");
    }
    /**
     * crc校验
     * @param block
     * @param crc
     * @throws IOException
     */
    private void writeCRC(byte[] block, CRC crc) throws IOException {
        byte[] crcBytes = new byte[crc.getCRCLength()];
        long crcValue = crc.calcCRC(block);
        for (int i = 0; i < crc.getCRCLength(); i++) {
            crcBytes[crc.getCRCLength() - i - 1] = (byte) ((crcValue >> (8 * i)) & 0xFF);
        }
        byteBuffer.put(crcBytes);
    }
/**
 * 获取mina返回数据
 * @param timer
 * @param writeFuture
 * @param session
 * @return
 * @throws IOException
 * @throws TimeoutException
 */
    private int readByte(Timer timer,WriteFuture writeFuture,IoSession session) throws IOException, TimeoutException {
    	if(writeFuture.isWritten()){
			ReadFuture readFuture = session.read();
			//等待消息响应
			readFuture.awaitUninterruptibly();
			//是否响应成功
			if(readFuture.isRead()){
				//获取消息
				Object message = readFuture.getMessage();
				return (int)message;
			}
		}
    	return 0;
    }
    /** 
     * 对象转数组 
     * @param obj 
     * @return 
     */  
    public byte[] toByteArray (Object obj) {     
        byte[] bytes = null;     
        ByteArrayOutputStream bos = new ByteArrayOutputStream();     
        try {       
            ObjectOutputStream oos = new ObjectOutputStream(bos);        
            oos.writeObject(obj);       
            oos.flush();        
            bytes = bos.toByteArray ();     
            oos.close();        
            bos.close();       
        } catch (IOException ex) {       
            ex.printStackTrace();  
        }     
        return bytes;   
    }  
   /**
    * 给设备发送结束信号
    * @throws IOException
    */
    protected void sendEOT(WriteFuture writeFuture,IoSession session) throws IOException {
        int errorCount = 0;
        Timer timer = new Timer(BLOCK_TIMEOUT);
        int character;
        while (errorCount < 10) {
//            sendByte(EOT);
        	session.write(EOT);
            try {
                character = readByte(timer.start(),writeFuture,session);//读取设备返回数据
                if (character == ACK) {
                	byte[] b0=new byte[133];
                	session.write(b0);
                    return;
                } else if (character == CAN) {
                    throw new IOException("Transmission terminated");
                }
            } catch (TimeoutException ignored) {
            }
            errorCount++;
        }
    }
}

