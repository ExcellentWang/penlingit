package com.ontheroad.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketService {
	public static void main(String[] args) throws IOException{
		 try {
	            //创建Socket对象
			   Socket socket = new Socket("127.0.0.1", 8888);
	            
	            //根据输入输出流和服务端连接
	            OutputStream outputStream=socket.getOutputStream();//获取一个输出流，向服务端发送信息
	            PrintWriter printWriter=new PrintWriter(outputStream);//将输出流包装成打印流
	            printWriter.print("服务端你好，我是客户1\n");
	            printWriter.flush();
	            socket.shutdownOutput();//关闭输出流
	            
	            
	            
	            InputStream inputStream=socket.getInputStream();//获取一个输入流，接收服务端的信息
	            InputStreamReader inputStreamReader=new InputStreamReader(inputStream);//包装成字符流，提高效率
	            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);//缓冲区
	            String info="";
	            String temp=null;//临时变量
	            while((temp=bufferedReader.readLine())!=null){
	                info+=temp;
	                System.out.println("客户端接收服务端发送信息："+info);
	            }
	            
	            //关闭相对应的资源
	            bufferedReader.close();
	            inputStream.close();
	            printWriter.close();
	            outputStream.close();
	            socket.close();
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		
		
		/* try {
			              // 1、创建客户端Socket，指定服务器地址和端口
			              // Socket socket=new Socket("127.0.0.1",5200);
			              Socket socket = new Socket("127.0.0.1", 8888);
			              System.out.println("客户端启动成功");
			              // 2、获取输出流，向服务器端发送信息
			              // 向本机的52000端口发出客户请求
			              String str = "hello";			           
			              InputStream   in_nocode   =   new   ByteArrayInputStream(str.getBytes());   
			              
			              BufferedReader br = new BufferedReader(new InputStreamReader(in_nocode));
			              // 由系统标准输入设备构造BufferedReader对象
			              PrintWriter write = new PrintWriter(socket.getOutputStream());
			              // 由Socket对象得到输出流，并构造PrintWriter对象
			              //3、获取输入流，并读取服务器端的响应信息 
			              BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			              // 由Socket对象得到输入流，并构造相应的BufferedReader对象
			              String readline;
			              readline = br.readLine(); // 从系统标准输入读入一字符串
		                  // 若从标准输入读入的字符串为 "end"则停止循环
		                  write.println(readline);
		                  // 将从系统标准输入读入的字符串输出到Server
		                  write.flush();
		                  // 刷新输出流，使Server马上收到该字符串
		                  System.out.println("Client:" + readline);
		                  // 在系统标准输出上打印读入的字符串
		                  System.out.println("Server:" + in.readLine());
		                  // 从Server读入一字符串，并打印到标准输出上
		                  readline = br.readLine(); // 从系统标准输入读入一字符串
		                  
		                  //写完以后进行读操作  
		                   Reader reader = new InputStreamReader(socket.getInputStream());  
		                   char chars[] = new char[64];  
		                   int len;  
		                   StringBuffer sb = new StringBuffer();  
		                   while ((len=reader.read(chars)) != -1) {  
		                      sb.append(new String(chars, 0, len));  
		                   }  
		                   System.out.println("from server: " + sb);
		                  
			              //4、关闭资源 
			              write.close(); // 关闭Socket输出流
			              in.close(); // 关闭Socket输入流			   
			              socket.close(); // 关闭Socket
			              reader.close(); 
			          } catch (Exception e) {
			              System.out.println("can not listen to:" + e);// 出错，打印出错信息
			          }*/
    }
    
}