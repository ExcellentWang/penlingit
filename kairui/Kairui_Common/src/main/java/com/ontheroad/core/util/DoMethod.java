package com.ontheroad.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
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

/**
  * @ClassName: DoMethod
  * @Description: 请求java的方法
  * @author 肖勇
  * @date 2015-9-2
 */
public class DoMethod {


	/**
	  * @Description: post 方法请求服务器
	  * @param url 请求地址
	  * @param params 请求参数
	  * @param connectTimeout 链接超时时间
	  * @param readTimeout 响应超时时间
	  * @param secretKey 加密秘钥
	  * @return
	  * @throws IOException
	  * @author 肖勇
	  * @date 2015-9-2
	 */
	public static String doPost(String url, Map<String, String> params, int connectTimeout, int readTimeout) throws IOException {
		//添加默认参数
		params.put("appKey", ConfigProperty.getProperty("java_appKey"));
		params.put("format", "json");
		params.put("ver", "1.0");
		return doPost(url, params, connectTimeout, readTimeout, null, ConfigProperty.getProperty("java_secretKey"));
	}
	
	
	private static String doPost(String url, Map<String, String> params, 
			int connectTimeout, int readTimeout, Map<String, String> headerMap,String secretKey) throws IOException {
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
			String ctype = "application/x-www-form-urlencoded;charset=UTF-8";
			conn = getConnection(new URL(url), "POST", ctype, headerMap);
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			
			String query = buildQuery(params);
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
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

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
				query.append(name).append("=").append(URLEncoder.encode(value, "UTF-8"));
			}
		}

		return query.toString();
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

}