/*package com.ontheroad.core.util;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.ibatis.reflection.Reflector;
import org.apache.ibatis.reflection.invoker.Invoker;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.util.StringUtils;



public class ExcelUtil {
	

	public static<T> void export(HttpServletResponse response, String fileName, String[] headerNames, String[] headerKeys, List<T> list) {
		export(response, fileName, headerNames, headerKeys, list, "yyyy-MM-dd");
	}
	
	public static<T> void export(HttpServletResponse response, String fileName, String[] headerNames, String[] headerKeys, List<T> list, String dateFormat) {
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename="
				+ encodingFileName(fileName));
		response.setContentType("application/octet-stream");
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			 // 声明一个工作薄
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        // 生成一个表格
	        HSSFSheet sheet = workbook.createSheet("Sheet1");
	        // 设置表格默认列宽度为18个字节
	        sheet.setDefaultColumnWidth(18);
	       
	        HSSFRow row = sheet.createRow(0);
	        for(int colIndex = 0; colIndex < headerNames.length; colIndex++){
	        	Cell cell = row.createCell(colIndex);
	        	cell.setCellValue(headerNames[colIndex]);
	        }
	        for (int rowIndex = 0; rowIndex < list.size() ; rowIndex++) {
				row = sheet.createRow(rowIndex + 1);
				T t = list.get(rowIndex);
				Reflector reflector = Reflector.forClass(t.getClass());
				
				for(int colIndex = 0; colIndex < headerKeys.length; colIndex++){
		        	Cell cell = row.createCell(colIndex);
					Invoker invoker = reflector.getGetInvoker(headerKeys[colIndex]);
					Object fieldValue = invoker.invoke(t, new Object[] {});
					
					if(fieldValue == null) {
						continue;
					}
					Object type = invoker.getType();
					if (type.equals(BigDecimal.class)) {		
						cell.setCellValue(((BigDecimal)fieldValue).doubleValue());
					} else if (type.equals(Integer.class)) {
						cell.setCellValue((Integer)fieldValue);
					}else if (type.equals(Long.class)) {
						cell.setCellValue(((Long)fieldValue).toString());
					} else if (type.equals(Double.class)) {
						cell.setCellValue(((Double)fieldValue).doubleValue());
					} else if (type.equals(Date.class)) {
						cell.setCellValue(DateUtils2.format((Date)fieldValue, dateFormat));
					}else {
						cell.setCellValue((String)fieldValue);
					}
		        	
		        }			
			}        
	        workbook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(out);
        }
	}
	
	public static String encodingFileName(String fileName) { 
        String returnFileName = ""; 
        try { 
            returnFileName = URLEncoder.encode(fileName, "UTF-8"); 
            returnFileName = StringUtils.replace(returnFileName, "+", "%20"); 
            if (returnFileName.length() > 150) { 
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1"); 
                returnFileName = StringUtils.replace(returnFileName, " ", "%20"); 
            } 
        } catch (UnsupportedEncodingException e) { 
            e.printStackTrace(); 
           
        } 
        return returnFileName; 
    } 
}
*/