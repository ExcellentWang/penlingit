package com.kairui.test.utils;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class PostClient {

    public static String doPost(String url, Map<String, String> params, Map<String, File> fileParams,
                                int connectTimeout, int readTimeout, Map<String, String> headerMap, String secretKey) throws IOException {

        if (fileParams != null && fileParams.size() > 0) {
            return doPostWithFile(url, params, fileParams, connectTimeout, readTimeout, headerMap, secretKey);
        } else {
            return doPost(url, params, connectTimeout, readTimeout, headerMap, secretKey);
        }
    }

    public static String doPost(String url, String body) throws IOException {
        return doPost(url, body, "UTF-8", 3000, 15000, null);
    }

    public static String doPost(String url, String body, String charset, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            String ctype = "application/x-www-form-urlencoded;charset=" + charset;
            conn = getConnection(new URL(url), "POST", ctype, headerMap);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            byte[] content = {};
            if (body != null) {
                content = body.getBytes(charset);
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

            String ctype = "application/x-www-form-urlencoded;charset=UTF-8";
            conn = getConnection(new URL(url), "POST", ctype, headerMap);
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);

            System.out.println("---入参：" + new Gson().toJson(params));

            String query = buildQuery(params);
            byte[] content = {};
            if (query != null) {
                content = query.getBytes("UTF-8");
            }

            out = conn.getOutputStream();
            out.write(content);
            rsp = getResponseAsString(conn);

            System.out.println("---出参：" + rsp);
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

    public static HttpURLConnection getConnection(URL url, String method,
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
        if (url.toString().startsWith("https")) {
            HostnameVerifier hostNameVerify = new HostnameVerifier() {
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };
            ((HttpsURLConnection) conn).setHostnameVerifier(hostNameVerify);
            try {
                ((HttpsURLConnection) conn).setSSLSocketFactory(initSSLSocketFactory());
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
                return new X509Certificate[]{};
            }
        }
        TrustManager[] tm = {new MyX509TrustManager()};

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

                query.append(name).append("=")
                        .append(URLEncoder.encode(value, "UTF-8"));
            }
        }

        return query.toString();
    }

    public static String doPostWithFile(String url,
                                        Map<String, String> params, Map<String, File> fileParams,
                                        int connectTimeout, int readTimeout, Map<String, String> headerMap,
                                        String secretKey) throws IOException {
        String boundary = System.currentTimeMillis() + ""; // 随机分隔线
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

            try {
                String ctype = "multipart/form-data;charset=UTF-8;boundary="
                        + boundary;
                conn = getConnection(new URL(url), "POST", ctype, headerMap);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }

            try {
                out = conn.getOutputStream();

                byte[] entryBoundaryBytes = ("\r\n--" + boundary + "\r\n")
                        .getBytes("UTF-8");

                // 组装文本请求参数
                Set<Entry<String, String>> textEntrySet = params.entrySet();
                for (Entry<String, String> textEntry : textEntrySet) {
                    byte[] textBytes = getTextEntry(textEntry.getKey(),
                            textEntry.getValue(), "UTF-8");
                    out.write(entryBoundaryBytes);
                    out.write(textBytes);
                }

                // 组装文件请求参数
                Set<Entry<String, File>> fileEntrySet = fileParams.entrySet();
                for (Entry<String, File> fileEntry : fileEntrySet) {
                    File fileItem = fileEntry.getValue();

                    byte[] content = getFileContent(fileItem);

                    byte[] fileBytes = getFileEntry(fileEntry.getKey(),
                            fileItem.getName(), getMimeType(content), "UTF-8");
                    out.write(entryBoundaryBytes);
                    out.write(fileBytes);
                    out.write(content);
                }

                // 添加请求结束标志
                byte[] endBoundaryBytes = ("\r\n--" + boundary + "--\r\n")
                        .getBytes("UTF-8");
                out.write(endBoundaryBytes);
                rsp = getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }
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

    private static byte[] getTextEntry(String fieldName, String fieldValue,
                                       String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\"\r\nContent-Type:text/plain\r\n\r\n");
        entry.append(fieldValue);
        return entry.toString().getBytes(charset);
    }

    private static byte[] getFileEntry(String fieldName, String fileName,
                                       String mimeType, String charset) throws IOException {
        StringBuilder entry = new StringBuilder();
        entry.append("Content-Disposition:form-data;name=\"");
        entry.append(fieldName);
        entry.append("\";filename=\"");
        entry.append(URLEncoder.encode(fileName, "UTF-8"));
        entry.append("\"\r\nContent-Type:");
        entry.append(mimeType);
        entry.append("\r\n\r\n");
        return entry.toString().getBytes(charset);
    }

    /**
     * 获取文件的真实后缀名。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     *
     * @param bytes 文件字节流
     * @return JPG, GIF, PNG or null
     */
    public static String getFileSuffix(byte[] bytes) {
        if (bytes == null || bytes.length < 10) {
            return null;
        }

        if (bytes[0] == 'G' && bytes[1] == 'I' && bytes[2] == 'F') {
            return "GIF";
        } else if (bytes[1] == 'P' && bytes[2] == 'N' && bytes[3] == 'G') {
            return "PNG";
        } else if (bytes[6] == 'J' && bytes[7] == 'F' && bytes[8] == 'I'
                && bytes[9] == 'F') {
            return "JPG";
        } else if (bytes[0] == 'B' && bytes[1] == 'M') {
            return "BMP";
        } else {
            return null;
        }
    }

    /**
     * 获取文件的真实媒体类型。目前只支持JPG, GIF, PNG, BMP四种图片文件。
     *
     * @param bytes 文件字节流
     * @return 媒体类型(MEME-TYPE)
     */
    public static String getMimeType(byte[] bytes) {
        String suffix = getFileSuffix(bytes);
        String mimeType;

        if ("JPG".equals(suffix)) {
            mimeType = "image/jpeg";
        } else if ("GIF".equals(suffix)) {
            mimeType = "image/gif";
        } else if ("PNG".equals(suffix)) {
            mimeType = "image/png";
        } else if ("BMP".equals(suffix)) {
            mimeType = "image/bmp";
        } else {
            mimeType = "application/octet-stream";
        }

        return mimeType;
    }

    public static byte[] getFileContent(File file) throws IOException {
        byte[] content = null;

        if (file != null && file.exists()) {
            InputStream in = null;
            ByteArrayOutputStream out = null;

            try {
                in = new FileInputStream(file);
                out = new ByteArrayOutputStream();
                int ch;
                while ((ch = in.read()) != -1) {
                    out.write(ch);
                }
                content = out.toByteArray();
            } finally {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
        }
        return content;
    }

    public static String getResponseAsString(HttpURLConnection conn)
            throws IOException {
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), "UTF-8");
        } else {
            String msg = getStreamAsString(es, "UTF-8");
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":"
                        + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    public static String getStreamAsString(InputStream stream, String charset)
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