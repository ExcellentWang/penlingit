package com.ontheroad.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 工具类
 * @author mazhengzheng
 *
 */
public class CommonUtil {
	private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);
	/**
	 * http请求
	 * @param requestUrl	url(如果含有中文、特殊字符等需要自己编码)
	 * @param method		请求方式(默认为GET)
	 * @param body			请求体(不需要编码)
	 * @param urlParam		url参数(不需要编码)
	 * @param charSet		编码(默认使用UTF-8)
	 * @param head			请求头
	 * @return				响应字符串
	 */
	public static String httpRequest(String requestUrl,String method,String body,
			Map<String, String> urlParam,String charSet,Map<String, String> head){
		HttpURLConnection httpUrlConn=null;
		OutputStream os=null;
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		
		charSet=charSet==null?"UTF-8":charSet;
		method=method==null?"GET":method.toUpperCase();
		requestUrl=getUrl(urlParam, requestUrl, charSet);
		
		try {
			URL url=new URL(requestUrl);
			httpUrlConn=(HttpURLConnection)url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
		    httpUrlConn.setRequestMethod(method);
		    httpUrlConn.setConnectTimeout(13000);
		    httpUrlConn.setReadTimeout(13000);
		    boolean isJson=false;
		    if(head!=null){
		    	for(String key:head.keySet()){
		    		if("Content-type".equalsIgnoreCase(key) && ("application/json".equalsIgnoreCase(head.get(key)) || "text/json".equalsIgnoreCase(head.get(key)))){
		    			isJson=true;
		    		}
		    		httpUrlConn.setRequestProperty(key, head.get(key));
		    	}
		    }
		    httpUrlConn.connect();
		    
		    if("POST".equals(method) && body!=null){
		    	if(!isJson){
		    		body=body.replaceAll("%", "%25");
		    	}
		    	os=httpUrlConn.getOutputStream();
		    	os.write(strToByte(body,charSet));
		    	os.flush();
		    }
		    
		    is=httpUrlConn.getInputStream();
		    isr=new InputStreamReader(is, charSet);
		    br=new BufferedReader(isr);
		    
		    StringBuilder sb=new StringBuilder();
		    String line=null;
		    while((line=br.readLine())!=null){
		    	sb.append(line);
		    }
		    
		    return sb.toString();
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally{
			try {
				if(os!=null){
					os.close();
				}
				if(br!=null){
					br.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(is!=null){
					is.close();
				}
				if(httpUrlConn!=null){
					httpUrlConn.disconnect();
				}
			} catch (IOException e) {
				log.error("",e);
			}
		}
		
	}
	
	
	public static String httpRequest(String requestUrl,String method,String body,
			Map<String, String> urlParam,String charSet,Map<String, String> head, int timeout){
		HttpURLConnection httpUrlConn=null;
		OutputStream os=null;
		InputStream is=null;
		InputStreamReader isr=null;
		BufferedReader br=null;
		
		charSet=charSet==null?"UTF-8":charSet;
		method=method==null?"GET":method.toUpperCase();
		requestUrl=getUrl(urlParam, requestUrl, charSet);
		
		try {
			URL url=new URL(requestUrl);
			httpUrlConn=(HttpURLConnection)url.openConnection();
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
		    httpUrlConn.setRequestMethod(method);
		    httpUrlConn.setConnectTimeout(timeout);
		    httpUrlConn.setReadTimeout(timeout);
		    
		    boolean isJson=false;
		    if(head!=null){
		    	for(String key:head.keySet()){
		    		if("Content-type".equalsIgnoreCase(key) && ("application/json".equalsIgnoreCase(head.get(key)) || "text/json".equalsIgnoreCase(head.get(key)))){
		    			isJson=true;
		    		}
		    		httpUrlConn.setRequestProperty(key, head.get(key));
		    	}
		    }
		    httpUrlConn.connect();
		    
		    if("POST".equals(method) && body!=null){
		    	if(!isJson){
		    		body=body.replaceAll("%", "%25");
		    	}
		    	os=httpUrlConn.getOutputStream();
		    	os.write(strToByte(body,charSet));
		    	os.flush();
		    }
		    
		    is=httpUrlConn.getInputStream();
		    isr=new InputStreamReader(is, charSet);
		    br=new BufferedReader(isr);
		    
		    StringBuilder sb=new StringBuilder();
		    String line=null;
		    while((line=br.readLine())!=null){
		    	sb.append(line);
		    }
		    
		    return sb.toString();
		} catch (MalformedURLException e) {
			log.error("",e);
		} catch (IOException e) {
			log.error("", e);
		} catch (Exception e) {
			log.error("HTTP请求异常：",e);
		}finally{
			try {
				if(os!=null){
					os.close();
				}
				if(br!=null){
					br.close();
				}
				if(isr!=null){
					isr.close();
				}
				if(is!=null){
					is.close();
				}
				if(httpUrlConn!=null){
					httpUrlConn.disconnect();
				}
			} catch (IOException e) {
				log.error("",e);
			}
		}
		
		return null;
	}
	/**
	 * 字符串转换成字节
	 * @param str		待转换字符串
	 * @param charSet	编码(默认使用Java内部编码)
	 * @return
	 */
	public static byte[] strToByte(String... params){
		if(params==null || params.length==0 || params[0]==null){
			return null;
		}
		
		if(params.length==1 || params[1]==null){
			return params[0].getBytes();
		}
		
		try {
			return params[0].getBytes(params[1]);
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
		}
		
		return null;
	}
	
	/**
	 * 获取url
	 * @param urlParam		url参数
	 * @param requestUrl	url
	 * @param charSet		编码(默认UTF-8)
	 * @return
	 */
	public static String getUrl(Map<String, String> urlParam,
			String... params){
		String requestUrl="";
		String charSet="UTF-8";
		if(params!=null){
			if(params.length>0 && params[0]!=null){
				requestUrl=params[0];
			}
			if(params.length>1 && params[1]!=null){
				charSet=params[1];
			}
		}
		
		if(urlParam!=null && !urlParam.isEmpty()){
			String urlEnd="";
			StringBuilder sb=new StringBuilder();
			
			int index=requestUrl.indexOf("#");
			if(index>=0){
				urlEnd=requestUrl.substring(index);
				requestUrl=requestUrl.substring(0, index);
			}
			
			sb.append(requestUrl);
			index=requestUrl.indexOf("?");
			if(index<0){
				sb.append("?");
			}else{
				if(requestUrl.charAt(requestUrl.length()-1)!='&' && 
						requestUrl.charAt(requestUrl.length()-1)!='?'){
					sb.append("&");
				}
			}
			
			try {
				for(String key:urlParam.keySet()){
					sb.append(URLEncoder.encode(key, charSet));
					sb.append("=");
					sb.append(URLEncoder.encode(urlParam.get(key), charSet));
					sb.append("&");
				}
			} catch (UnsupportedEncodingException e) {
				log.error("",e);
			}
			
			if(sb.charAt(sb.length()-1)=='&'){
				sb.deleteCharAt(sb.length()-1);
			}
			
			sb.append(urlEnd);
			
			return sb.toString();
		}
		
		return requestUrl;
	}
	
	/**
	 * 解析json成Map
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> parseJsonToMap(String jsonStr){
		log.info("jsonStr="+jsonStr);
		
		/*Map<String, Object> resMap=new HashMap<String, Object>();
		
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			resMap=objectMapper.readValue(jsonStr, Map.class);
		} catch (JsonParseException e) {
			log.error("",e);
		} catch (JsonMappingException e) {
			log.error("",e);
		} catch (IOException e) {
			log.error("",e);
		}
		
		return resMap;*/
		return (Map<String,Object>)JSON.parse(jsonStr);
	}
	
	/*public static void main(String[] args) throws IOException{
		PropertyConfigurator.configure("D:/Daboo_java/member/branches/MEMBERS/MEMBER_Service/src/main/resources/log4j.properties");//加载.xml文件
		//CommonUtil.sendRequestToNet(appkey, token, bodyParam, urlParam, url)http://wx.wdlove.cn/
		TreeMap<String,String> t = new TreeMap<String,String>();
		t.put("appkey", "6e4ac63bef54xIoXR9Rxcfc4317253ca9");
		t.put("date", "2015-11-26");
		
		
		String sign = Md5Util.md5Signature(t, "ab6eb29b53c4f5qwert351667c2f23329");
		//sendRequestToNet("6e4ac63bef54xIoXR9Rxcfc4317253ca9","","appkey=6e4ac63bef54xIoXR9Rxcfc4317253ca9&date=2015-11-26&sign="+sign,null,"http://192.168.1.140:10010/MemberSystem/GetMemberSystemRecords");
		String s = CommonUtil.doPost("http://192.168.1.140:10010/MemberSystem/GetMemberSystemRecords", t, 300000, 1500000, null, "ab6eb29b53c4f5qwert351667c2f23329");
		//httpRequest("http://localhost:8080/weixin/excel", "POST", "a=%哈&b=哈哈哈", null, "UTF-8", null);
		System.out.println(s);
	}*/
	/**
	 * @FunName:sendRequestToNet
	 * @Description:发送请求
	 * @param: Integer userId  用户id
	 *         Integer type    用户类型
	 *         String  appkey  
	 *         String  token   
	 *         String  bodyParam  post提交的参数
	 *         String  method     请求的方法名
	 * @return   结果集
	 * @Author:fengchao
	 * @Create Date: 2015-04-09
	 */
	public static String sendRequestToNet(Integer userId,Integer type,String appkey,String token,String bodyParam,String url){
		
		Map<String, String> head = new HashMap<String,String>();
		head.put("Authorization","Bearer "+token);
		head.put("ClientId",appkey);
    	head.put("content-type", "application/json");
    	//设置3秒超时
		String jsonString = CommonUtil.httpRequest(url,"POST",bodyParam,null,"UTF-8", head, 3000);
		return jsonString;
	}
	/**
	 * @FunName:sendRequestToNet
	 * @Description:发送请求
	 * @param: String  appkey  
	 *         String  token   
	 *         String  bodyParam  post提交的参数
	 *         String  method     请求的方法名
	 * @return   结果集
	 * @Author:fengchao
	 * @Create Date: 2015-04-09
	 */
	public static String sendRequestToNet(String bodyParam,String url){
		
		Map<String, String> head = new HashMap<String,String>();
    	head.put("content-type", "application/json");
		String jsonString = CommonUtil.httpRequest(url,"POST",bodyParam,null,"UTF-8", head);
		return jsonString;
	    
	}
	/**
	 * 发送请求
	 */
	public static String sendRequestToNet(String appkey,String token,String bodyParam, Map<String, String> urlParam,String url){
		
		Map<String, String> head = new HashMap<String,String>();
		head.put("Authorization","Bearer "+ token);
		head.put("ClientId",appkey);
    	head.put("content-type", "application/json");
		String jsonString = CommonUtil.httpRequest(url,"POST",bodyParam, urlParam, "UTF-8", head);
		return jsonString;
	    
	}
	
	
	public static String doPost(String url, Map<String, String> params,
			int connectTimeout, int readTimeout, Map<String, String> headerMap,
			String secretKey) throws IOException {
		HttpURLConnection conn = null;
		OutputStream out = null;
		String rsp = null;
		try {

			TreeMap<String, String> treeMap = new TreeMap<String, String>();
			if (params != null) {
				treeMap.putAll(params);
			}

			String sign = Md5Util.md5Signature(treeMap, secretKey);
			params.put("sign", sign);
			System.out.println("ddddddddddddddddddddd-"+sign);
			String ctype = "application/x-www-form-urlencoded;charset=UTF-8";
			conn = getConnection(new URL(url), "POST", ctype, headerMap);
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			
			String query = buildQuery(params);
			System.out.println(query);
			byte[] content = {};
			if (query != null) {
				content = query.getBytes("UTF-8");
			}

			out = conn.getOutputStream();
			out.write(content);
			rsp = getResponseAsString(conn);

		} catch (IOException e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rsp;
	}
	
	private static HttpURLConnection getConnection(URL url, String method,
			String ctype, Map<String, String> headerMap) throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod(method);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
		conn.setRequestProperty("Content-Type", ctype);
		 if (headerMap != null) {
			 for (Map.Entry<String, String> entry : headerMap.entrySet()) {
				 conn.setRequestProperty(entry.getKey(), entry.getValue());
			 }
		 }
		
		 //如果是https请求，初始化https请求信息
		if(url.toString().startsWith("https")){
			HostnameVerifier hostNameVerify = new HostnameVerifier() {
				public boolean verify(String urlHostName, SSLSession session) {
					return true;
				}
			};
			((HttpsURLConnection)conn).setHostnameVerifier(hostNameVerify);
			try {
				((HttpsURLConnection)conn).setSSLSocketFactory(initSSLSocketFactory());
			} catch (Exception e1) {
				throw new IOException(e1);
			}
		}
		
		return conn;
	}
	
	protected static String getResponseAsString(HttpURLConnection conn)
			throws IOException {
		InputStream es = conn.getErrorStream();
		if (es == null) {
			return getStreamAsString(conn.getInputStream(), "UTF-8");
		} else {
			String msg = getStreamAsString(es, "UTF-8");
			if (StringUtils.isEmpty(msg)) {
				throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
			} else {
				throw new IOException(msg);
			}
		}
	}
	
	private static String getStreamAsString(InputStream stream, String charset)
			throws IOException {
		try {
			Reader reader = new InputStreamReader(stream, charset);
			StringBuilder response = new StringBuilder();

			final char[] buff = new char[1024];
			int read = 0;
			while ((read = reader.read(buff)) > 0) {
				response.append(buff, 0, read);
			}

			return response.toString();
		} finally {
			if (stream != null) {
				stream.close();
			}
		}
	}
	
	public static String buildQuery(Map<String, String> params)
			throws IOException {
		if (params == null || params.isEmpty()) {
			return null;
		}

		StringBuilder query = new StringBuilder();
		Set<Entry<String, String>> entries = params.entrySet();
		boolean hasParam = false;

		for (Entry<String, String> entry : entries) {
			String name = entry.getKey();
			String value = entry.getValue();
			// 忽略参数名或参数值为空的参数
			if (!StringUtils.isEmpty(name) || !StringUtils.isEmpty(value)) {
				if (hasParam) {
					query.append("&");
				} else {
					hasParam = true;
				}

				query.append(name).append("=")
						.append(value);
			}
		}

		return query.toString();
	}
	
	
	public static SSLSocketFactory initSSLSocketFactory() throws Exception {

		class MyX509TrustManager implements X509TrustManager {

			public MyX509TrustManager() throws Exception {
				// do nothing
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {

			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {

				/*
				 * log.info("authType is " + authType);
				 * log.info("cert issuers");
				 * 
				 * try{ for (int i = 0; i < chain.length; i++) { log.info("\t" +
				 * chain[i].getIssuerX500Principal().getName()); log.info("\t" +
				 * chain[i].getIssuerDN().getName()); chain[i].checkValidity();
				 * } }catch(CertificateExpiredException ex){
				 * log.error("checkDate: Certificate has expired");
				 * }catch(CertificateNotYetValidException yet){
				 * log.error("checkDate: Certificate is not yet valid");
				 * }catch(Exception ee){ log.error("Error: "+ee.getMessage()); }
				 */

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		}
		TrustManager[] tm = { new MyX509TrustManager() };

		System.setProperty("https.protocols", "TLSv1");
		SSLContext sslContext = SSLContext.getInstance("TLSv1", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

		return sslSocketFactory;
	}
}

