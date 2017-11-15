package com.ontheroad.core.util;

import com.aliyun.openservices.ClientConfiguration;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @author 马正正
 * @ClassName: FileUtil
 * @Description: 文件操作公共方法
 * @date 2015年6月24日
 */
public class FileUtil {
    private static Log logger = LogFactory.getLog(FileUtil.class);
    /**
     * OSSClient对象
     */
    private static OSSClient client;

    static {
        init();
    }

    /**
     * @Description: 初始化
     * @author 马正正
     * @date 2015年7月8日
     */
    private static void init() {
        ClientConfiguration conf = new ClientConfiguration();
        conf.setMaxConnections(500);        //设置HTTP最大连接数为500
        conf.setConnectionTimeout(20000);    //设置TCP连接超时为20秒
        conf.setMaxErrorRetry(5);            //设置最大的重试次数为5
        conf.setSocketTimeout(100000);        //设置Socket传输数据超时的时间为100秒

        String endpoint = ConfigProperty.getProperty("oss.endpoint");
        String accessKeyId = ConfigProperty.getProperty("oss.accessKeyId");
        String accessKeySecret = ConfigProperty.getProperty("oss.accessKeySecret");
//        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
//        String accessKeyId = "O9mt0MvwXsJAmmlu";
//        String accessKeySecret = "TYZ5TnLme8OoF1BJkYAgznXiPV5JQC";
        client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
    }

    /**
     * @param input    输入流
     * @param fileName 文件名(可以是url形式a/b/c.txt,开头不能是/)
     * @param lenth    文件大小(字节数)
     * @return 文件url
     * @Description: 文件上传
     * @author 马正正
     * @date 2015年6月24日
     */
    public static String uploadFile(InputStream input, String fileName, long lenth) {
        logger.info("文件上传到阿里云");

        String bucketName = ConfigProperty.getProperty("oss.bucketName");
        String bucketUrl = ConfigProperty.getProperty("oss.oss.openoo-fileserver-chatfile");
//        String bucketName = "openoo-fileserver-chatfile";
//        String bucketUrl = "http://fscf.openoo.com/";
        String result = bucketUrl + fileName;                    //返回OSS文件地址 绝对地址

        ObjectMetadata objectMeta = new ObjectMetadata();    //OSS上传
        objectMeta.setContentLength(lenth);

        long startTime = System.currentTimeMillis();

        PutObjectResult put = client.putObject(bucketName, fileName, input, objectMeta);

        logger.info("返回的结果" + put.getETag() + "---文件上传到oss的时间 :" + (System.currentTimeMillis() - startTime));

        return result;
    }

    public static String uploadFile(MultipartFile image, String dir) throws IOException {
        if (image != null && org.apache.commons.lang3.StringUtils.isNotBlank(image.getOriginalFilename())) {
            String fileName = image.getOriginalFilename();
            //获取后缀
            String fileEx = fileName.substring(fileName.lastIndexOf("."));
            //新的文件名
            fileName = dir + UUID.randomUUID().toString().replace("-", "") + fileEx;
            //存储到oss
            return uploadFile(image.getInputStream(), fileName, image.getBytes().length * 1L);
        }
        return "";
    }

    /**
     * @param content  文件
     * @param fileName 文件名(可以是url形式a/b/c.txt,开头不能是/)
     * @return
     * @Description: 文件上传
     * @author 马正正
     * @date 2015年6月24日
     */
    public static String uploadFile(byte[] content, String fileName) {
        return uploadFile(new ByteArrayInputStream(content), fileName, content.length);
    }

    public static String uploadFile(File file, String fileName) {
        String path = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            path = uploadFile(is, fileName, file.length());
        } catch (FileNotFoundException e) {
            logger.error("", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
        return path;
    }

    /**
     * @param fileUrl 文件路径
     * @Description: 文件删除
     * @author 马正正
     * @date 2015年6月24日
     */
    public static void deleteFileOSS(String fileUrl) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(fileUrl)) {
            String bucketName = ConfigProperty.getProperty("oss.bucketName");
            fileUrl = fileUrl.replaceFirst("http://[^/]+/(.+)", "$1");
            client.deleteObject(bucketName, fileUrl);
        }
    }

    /**
     * 批量删除文件
     *
     * @param fileList
     */
    public static void deleteFileListOSS(List<String> fileList) {
        if (fileList != null && fileList.size() > 0) {
            for (String fileName : fileList) {
                deleteFileOSS(fileName);
            }
        }
    }
}
