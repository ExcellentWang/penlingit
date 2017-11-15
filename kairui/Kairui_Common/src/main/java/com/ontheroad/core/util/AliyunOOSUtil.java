package com.ontheroad.core.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 项目名称：Health_Business_Common<br/>
 * <p/>
 * 类名称：AliyunOOSUtil<br/>
 * 类描述：阿里云OOS签名<br/>
 * 创建人：邓强  <br/>
 * 创建时间：2016年12月27日 上午8:50:08<br/>
 * 修改人：  <br/>
 * 修改时间：<br/>
 * 修改备注：<br/>
 *
 * @version V1.0
 */

public class AliyunOOSUtil {
    // OSS域名，如http://oss-cn-hangzhou.aliyuncs.com
    public static final String endpoint = ConfigProperty.getProperty("oss.endpoint");
    // AccessKey请登录https://ak-console.aliyun.com/#/查看
    private static final String accessKeyId = ConfigProperty.getProperty("oss.accessKeyId");
    private static final String accessKeySecret = ConfigProperty.getProperty("oss.accessKeySecret");
    // 你之前创建的bucket，确保这个bucket已经创建
    public static final String bucketName = ConfigProperty.getProperty("oss.bucketName");
    //上传时间戳
    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static final String ENCODE_TYPE = "UTF-8";

    private static AliyunOOSUtil aliyunOOSUtil = null;

    private AliyunOOSUtil() {
    }

    public static AliyunOOSUtil getInstance() {
        if (aliyunOOSUtil == null) {
            synchronized (AliyunOOSUtil.class) {
                if (aliyunOOSUtil == null) {
                    aliyunOOSUtil = new AliyunOOSUtil();
                }
            }
        }
        return aliyunOOSUtil;
    }

    /**
     * ********************************** 文件上传签名start ******************************************
     */
    public static Map<String, String> getSigna() throws Exception {
        // 提交表单的URL为bucket域名
        String urlStr = "http://" + bucketName + "." + endpoint + ".aliyuncs.com";

        // 表单域
        Map<String, String> formFields = new LinkedHashMap<String, String>();

        formFields.put("host", urlStr);
        // OSSAccessKeyId
        formFields.put("accessid", accessKeyId);
        //失效时间时间30分钟
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        String strDate = formatIso8601Date(calendar.getTime());
        // policy
        String policy = "{\"expiration\": \"" + strDate + "\",\"conditions\": [[\"content-length-range\", 0, 104857600]]}";
        String encodePolicy = new String(Base64.encodeBase64(policy.getBytes()));
        formFields.put("policy", encodePolicy);
        // Signature
        String signaturecom = computeSignature(accessKeySecret, encodePolicy);
        formFields.put("signature", signaturecom);

        return formFields;
    }

    /**
     * ******************************************** 文件上传签名end ****************************************
     */


    private static String formatIso8601Date(Date date) {

        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);

        df.setTimeZone(new SimpleTimeZone(0, "GMT"));

        return df.format(date);

    }

    private static String percentEncode(String value) throws UnsupportedEncodingException {
        if (value == null) return null;
        return URLEncoder.encode(value, ENCODE_TYPE).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

    private static String computeSignature(String accessKeySecret, String encodePolicy)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        // convert to UTF-8
        byte[] key = accessKeySecret.getBytes("UTF-8");
        byte[] data = encodePolicy.getBytes("UTF-8");

        // hmac-sha1
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(new SecretKeySpec(key, "HmacSHA1"));
        byte[] sha = mac.doFinal(data);

        // base64
        return new String(Base64.encodeBase64(sha));
    }

    /**************************************************** 媒体服务start *****************************************************/
    /**
     * 前端js获取签名调用示例：
     * <code>
     * $.ajax(ctx + "/adver/getSnapshotUrl", {
     * type: "POST",
     * dataType: "json",
     * data: {
     * Action: "SubmitSnapshotJob",
     * Input: JSON.stringify({
     * Bucket: bucket,
     * Location: endpoint,
     * Object: fileName
     * }),
     * SnapshotConfig: JSON.stringify({
     * OutputFile: {
     * Bucket: bucket,
     * Location: endpoint,
     * Object: outputName
     * },
     * Time: 1000
     * })
     * },
     * success: function (__data__) {
     * todo something
     * },
     * error: function (__error__) {
     * console.log(__error__);
     * }
     * });
     * </code>
     */

    private static final String HTTP_METHOD = "GET";
    private static final String SEPARATOR = "&";
    private static final String EQUAL = "=";
    private static final String ALGORITHM = "HmacSHA1";

    /**
     * 获取带签名的媒体服务接口请求URL
     *
     * @param map 媒体服务接口特有的参数
     * @return
     * @throws Exception
     */
    public static String getMediaSignaUrl(Map<String, String> map) throws Exception {
        Map<String, String> parameterMap = new HashMap<String, String>();
        // 加入请求公共参数
        parameterMap.put("Version", "2014-06-18");
        parameterMap.put("AccessKeyId", accessKeyId); //此处请替换成您自己的AccessKeyId
        parameterMap.put("Timestamp", formatIso8601Date(new Date()));
        parameterMap.put("SignatureMethod", "HMAC-SHA1");
        parameterMap.put("SignatureVersion", "1.0");
        parameterMap.put("SignatureNonce", UUID.randomUUID().toString());
        parameterMap.put("Format", "json");

        // 加入方法特有参数
        if (map != null && map.size() > 0) {
            for (Entry<String, String> e : map.entrySet()) {
                parameterMap.put(e.getKey(), e.getValue());
            }
        }

        // 对参数进行排序
        List<String> sortedKeys = new ArrayList<String>(parameterMap.keySet());
        Collections.sort(sortedKeys);

        // 生成stringToSign字符
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (String key : sortedKeys) {
            // 此处需要对key和value进行编码
            String value = parameterMap.get(key);
            canonicalizedQueryString.append(SEPARATOR).append(percentEncode(key)).append(EQUAL).append(percentEncode(value));
        }

        // 此处需要对canonicalizedQueryString进行编码
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

        SecretKey key = new SecretKeySpec((accessKeySecret + SEPARATOR).getBytes(ENCODE_TYPE), SignatureMethod.HMAC_SHA1);
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(key);
        String signature = URLEncoder.encode(new String(
                new Base64().encode(mac.doFinal(stringToSign.toString().getBytes(ENCODE_TYPE))),
                ENCODE_TYPE), ENCODE_TYPE);

        // 生成请求URL
        StringBuilder requestURL;
        requestURL = new StringBuilder("http://mts.aliyuncs.com?");
        requestURL.append(URLEncoder.encode("Signature", ENCODE_TYPE)).append("=").append(signature);
        for (Entry<String, String> e : parameterMap.entrySet()) {
            requestURL.append("&").append(percentEncode(e.getKey())).append("=").append(percentEncode(e.getValue()));
        }

        return requestURL.toString();
    }


    /**************************************************** 媒体服务end *****************************************************/
}
