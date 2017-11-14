package com.ontheroad.core.util;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public class UploadUtil {
	public static String save(CommonsMultipartFile file,HttpServletRequest request){
		//获取本地文件地址
        String path = request.getSession().getServletContext().getRealPath("view/upload");  
        String fileName = new Date().getTime()+".jpg";   
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        String sImg= "https://sec.ldzhn.com/view/upload/"+fileName;
		return sImg;
	}
	public static String save2(CommonsMultipartFile file,HttpServletRequest request){
		//获取本地文件地址
        String path = request.getSession().getServletContext().getRealPath("view/upload");  
        String fileName = file.getOriginalFilename();   
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        String sImg= "https://sec.ldzhn.com/view/upload/"+fileName;
		return sImg;
	}
}
