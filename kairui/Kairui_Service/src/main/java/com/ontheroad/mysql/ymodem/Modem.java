package com.ontheroad.mysql.ymodem;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ReadFuture;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

public class Modem {
	 private static final Logger logger = Logger.getLogger(Modem.class);
    /* Protocol characters used */
    protected static  byte SOH = 0x01; /* Start Of Header */
    protected static final byte STX = 0x02; /* Start Of Text (used like SOH but means 1024 block size) */
    protected static final byte EOT = 0x04; /* End Of Transmission */
    protected static final byte ACK = 0x06; /* ACKnowlege */
    protected static final byte NAK = 0x15; /* Negative AcKnowlege */
    protected static final byte CAN = 0x18; /* CANcel character */

    protected static final byte CPMEOF = 0x1A;
    protected static final byte ST_C = 'C';
    protected static final byte YI = 0x31;

    protected static final int MAXERRORS = 10;

    protected static final int BLOCK_TIMEOUT = 1000;
    protected static final int REQUEST_TIMEOUT = 3000;
    protected static final int WAIT_FOR_RECEIVER_TIMEOUT = 60_000;
    protected static final int SEND_BLOCK_TIMEOUT = 10_000;
    public ByteBuffer byteBuffer=null;


    public Modem() {
    	super();
    	byteBuffer=ByteBuffer.allocate(133);
    }

    /**
     * 固件下发调用
     * @param file
     * @throws IOException
     */
    public void send(Path file,WriteFuture writeFuture,IoSession session ,String instructions) throws IOException {
    	session.getConfig().setUseReadOperation(true); 
		session.getConfig().setReaderIdleTime(2000);
        try (DataInputStream dataStream = new DataInputStream(Files.newInputStream(file))) {
            Timer timer = new Timer(Modem.WAIT_FOR_RECEIVER_TIMEOUT).start();
            logger.info("发送开始指令----------"+instructions);
            writeFuture=session.write(instructions); //1.向设备发送信号，开启固件升级
            boolean a1=wait1(timer, writeFuture, session);
            if(a1){
            	logger.info("------------------------1.向设备发送信号，开启固件升级-----------------");
            	session.write(YI);//发送1
            	boolean useCRC16 = waitReceiverRequest(timer, writeFuture, session);//等待设备返回C
            	CRC crc;
            	if (useCRC16){
            		crc = new CRC16();
            	}
            	else{
            		crc = new CRC8();
            	}
            	BasicFileAttributes readAttributes = Files.readAttributes(file, BasicFileAttributes.class);
            	String fileNameString = file.getFileName().toString() + (char)0 + ((Long) Files.size(file)).toString()+" "+ Long.toOctalString(readAttributes.lastModifiedTime().toMillis() / 1000);
            	byte[] fileNameBytes = Arrays.copyOf(fileNameString.getBytes(), 128);
            	logger.info("------------------------开始发送第一个包（告诉设备文件信息）-----------------");
            	sendBlock(0, Arrays.copyOf(fileNameBytes, 128), 128, crc,writeFuture,session);//2.发送第一个包（告诉设备文件信息）
            	waitReceiverRequest(timer,writeFuture,session);//等待设备返回C
            	//发送数据
            	byte[] block = new byte[128];
            	sendDataBlocks(dataStream, 1, crc, block,writeFuture,session);
            	sendEOT(writeFuture,session);
            }
        }
    }

   /**
    * 判断设备返回的数据是不是C
    * @param timer
    * @return
    * @throws IOException
    */
    public boolean waitReceiverRequest(Timer timer,WriteFuture writeFuture,IoSession session) throws IOException {
        int character;
        int num=0;
        while (num<3) {
            try {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                character = readByte(timer, writeFuture, session);
                num++;
                if (character == NAK)
                    return false;
                if (character == ST_C) {
                	 logger.info("------------------------设备返回C-----------------");
                    return true;
                }
            } catch (TimeoutException e) {
                throw new IOException("Timeout waiting for receiver");
            }
        }
        return false;
    }
    /**
     * 判断设备返回的数据是不是1
     * @param timer
     * @return
     * @throws IOException
     */
     public boolean wait1(Timer timer,WriteFuture writeFuture,IoSession session) throws IOException {
         int character;
         int num=0;
         while (num<3) {
        	 try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             try {
                 character = readByte(timer, writeFuture, session);
                 if (character == YI) {
                 	 logger.info("------------------------设备返回1-----------------");
                     return true;
                 }
             } catch (TimeoutException e) {
                 throw new IOException("Timeout waiting for receiver");
             }
         }
         return false;
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
    public void sendDataBlocks(DataInputStream dataStream, int blockNumber, CRC crc, byte[] block,WriteFuture writeFuture,IoSession session) throws IOException {
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
    public void sendBlock(int blockNumber, byte[] block, int dataLength, CRC crc,WriteFuture writeFuture,IoSession session) throws IOException {
        int errorCount;
        int character;
        Timer timer = new Timer(SEND_BLOCK_TIMEOUT);
        if (dataLength < block.length) {
            block[dataLength] = CPMEOF;//补码操作
        }
        errorCount = 0;
        while (errorCount < MAXERRORS) {
        	timer.start();
        	byteBuffer.put(SOH);//包头
        	byteBuffer.put((byte)blockNumber);//序号
        	byteBuffer.put((byte)~blockNumber);//反码
        	System.out.println(byteBuffer.array().length);
        	byteBuffer.put(block);//数据包
        	writeCRC(block, crc, byteBuffer);//crc校验
        	logger.info("------------------------封装的"+blockNumber+"数据包"+Arrays.toString(byteBuffer.array())+"-----------------");
        	writeFuture=session.write(byteBuffer.array());//mina发送数据
            // 从ByteBuffer中读取数据到byte数组中  
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
    private void writeCRC(byte[] block, CRC crc,ByteBuffer byteBuffer) throws IOException {
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
				logger.info("获取到设备返回消息------"+message);
				return (int)message;
			}
		}
    	logger.info("未获取到设备返回消息------");
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
    public void sendEOT(WriteFuture writeFuture,IoSession session) throws IOException {
        int errorCount = 0;
        Timer timer = new Timer(BLOCK_TIMEOUT);
        int character;
        while (errorCount < 10) {
        	session.write(EOT);
        	logger.info("------------------------发送结束信号-----------------");
            try {
                character = readByte(timer.start(),writeFuture,session);//读取设备返回数据
                if (character == ACK) {
                	byte[] b0=new byte[133];
                	session.write(b0);
                	logger.info("------------------------发送发送全0数据包结束这次固件升级，固件升级成功-----------------");
                    return;
                } else if (character == CAN) {
                    throw new IOException("Transmission terminated");
                }
            } catch (TimeoutException ignored) {
            }
            errorCount++;
        }
    }
    public static void main(String[] args) {
		int a=1;
		byte b=1;
		System.out.println(a==b);
	}
}

