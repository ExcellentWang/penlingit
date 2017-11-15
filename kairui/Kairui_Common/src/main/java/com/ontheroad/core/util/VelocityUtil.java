package com.ontheroad.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.VelocityException;


public class VelocityUtil {

	private static final String FILE_ENCODE = "UTF-8";

	public static String generateSource(String templateFile,
			Map<String, Object> paramMap) throws VelocityException {

		try {
			VelocityContext context = new VelocityContext();

//			Iterator<String> iterator = paramMap.keySet().iterator();
//			String paramKey = null;
//			while (iterator.hasNext()) {
//				paramKey = (String) iterator.next();
//				context.put(paramKey, paramMap.get(paramKey));
//			}
			
			Set<Entry<String, Object>> entrySet = paramMap.entrySet();
			for(Entry<String, Object> entry:entrySet){
				context.put(entry.getKey(), entry.getValue());
			}
			
			
			StringWriter sw = new StringWriter();

			VelocityEngine engine = getVelocityEngine();

			Template template = engine.getTemplate(templateFile, FILE_ENCODE);

			template.merge(context, sw);

			return sw.toString();

		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
			throw new VelocityException("ResourceNotFoundException");
		} catch (ParseErrorException e) {
			e.printStackTrace();
			throw new VelocityException("ParseErrorException");
		} catch (MethodInvocationException e) {
			e.printStackTrace();
			throw new VelocityException("MethodInvocationException");
		} catch (Exception e) {
			e.printStackTrace();
			throw new VelocityException("Exception");
		}

	}

	
	
	
	
		
		
		
		
		
	
	public static void printToJavaFile(String packageName, String className,
			String strData, String sourcePath) {
		try {
			String folderNames[] = packageName.split("\\.");
//			for (int i = 0; i < folderNames.length; i++) {
//				sourcePath += "/" + folderNames[i];
//			}
			
			StringBuffer sb = new StringBuffer(sourcePath);
			for (int i = 0; i < folderNames.length; i++) {
				sb.append( folderNames[i]);
			}
			sourcePath = sb.toString() ;
			
			File file = new File(sourcePath);
			if (!file.exists()) {
				file.mkdirs();
			} else {
				file = new File(sourcePath + "/" + className + ".java");
				if (file.exists()) {
					file.delete();
				}
			}
			PrintWriter printWriter = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream(sourcePath
							+ "/" + className + ".java"), FILE_ENCODE));
			printWriter.write(strData);
			printWriter.flush();
			printWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printToXMLFile(String xmlFileName, String strData,
			String sourcePath) {
		try {
			File file = new File(sourcePath);

			if (!file.exists()) {
				file.mkdirs();
			} else {
				file = new File(sourcePath + "/" + xmlFileName + ".xml");
				if (file.exists()) {
					file.delete();
				}
			}
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
					new FileOutputStream(sourcePath + "/" + xmlFileName
							+ ".xml"), FILE_ENCODE));
			printWriter.write(strData);
			printWriter.flush();
			printWriter.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static VelocityEngine getVelocityEngine() throws Exception {
		Properties properties = new Properties();
		// 可选值："class"--从classpath中读取，"file"--从文件系统中读取
		properties.setProperty("resource.loader", "class");
		// 如果从文件系统中读取模板，那么属性值为org.apache.velocity.runtime.resource.loader.FileResourceLoader
		properties
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		// properties.setProperty("jar.resource.loader.path", "jar:file:/" +
		// DAOTool.getLibFilePath() + ToolsConst.SEPARATOR +
		// ToolsConst.DAOTOOLS_JAR_NAME);
		properties.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.NullLogSystem");

		VelocityEngine engine = new VelocityEngine();

		engine.init(properties);
		return engine;

	}
	
	
	
	
	private static List<String> getJavaImportPart(File file){
		List<String> sourceList = new ArrayList<String>();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			
			String lineData = bufferedReader.readLine();
			while(lineData != null){
				if(lineData.startsWith("import ")){
					sourceList.add(lineData);
				}
				if(lineData.startsWith("public ")){
					break;
				}
				
				lineData = bufferedReader.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bufferedReader !=null){
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sourceList;
	}
	
	
	
	
	
	
	
	

}
