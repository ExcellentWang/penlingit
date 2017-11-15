package com.ontheroad.core.util;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class ImageServlet extends HttpServlet { 
	private static final long serialVersionUID = 4089618053519081488L;

	Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session=request.getSession();
        // 在内存中创建图象
        int width=120, height=40;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font("Times New Roman",Font.PLAIN,30));

        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);


        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }

        // 取随机产生的认证码(4位数字)
        String sRand="";
        for (int i=0;i<4;i++){
            String rand = "";
            /*String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                int temp1 = random.nextInt(2) % 2;
                if(temp1 == 0){
                	rand = String.valueOf((char)(random.nextInt(14) + temp)); 
                }else{
                	rand = String.valueOf((char)(15 + random.nextInt(11) + temp)); 
                }
            } else if( "num".equalsIgnoreCase(charOrNum) ) { */ 
            	rand =  String.valueOf(random.nextInt(10));  
            //}  
            sRand+=rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand,26*i+12,26);
        }

        // 将认证码存入SESSION
        session.setAttribute("seed",sRand);
        // 图象生效
        g.dispose();
        ServletOutputStream responseOutputStream =response.getOutputStream();
        // 输出图象到页面
        ImageIO.write(image, "JPEG", responseOutputStream);

        //以下关闭输入流！
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        doPost(req, resp);
    }

}
