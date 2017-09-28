package com.ontheroad.mysql.impl.AppServiceImpl;

import com.google.gson.Gson;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.service.AppService.QiniuService;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class QiniuServiceImpl implements QiniuService{

    private static final String accessKey = "Rx9dzIksnxyyHm-QQs5LiVx7ihda02OHMiRfsRIj";
    private static final String secretKey = "GTj2Y9__Qx8eRviwW9M9Jcpyxxj7FHaPPk1Y-gJc";
    private static final String bucket = "penlinsystem";
    private static final String domain = "http://oupduavl7.bkt.clouddn.com/";

    @Override
    public Map<Object, Object> upload(String localPath) {
        Map<Object, Object> map = new HashMap<Object, Object>();

        Configuration cfg = new Configuration(Zone.zone2());
        UploadManager uploadManager = new UploadManager(cfg);

        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            File f = new File(localPath);
            FileInputStream fis = new FileInputStream(f);
            Response response = uploadManager.put(fis, key, upToken, null, null);
            DefaultPutRet ret = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

            map.put("code", BaseConstant.appUserSuccessStatus);
            map.put("msg", "获取成功");
            map.put("extra",null);
            map.put("resultMap",  domain + ret.hash);
            return map;
        } catch (Exception ex) {
            if (ex instanceof QiniuException) {
                Response r = ((QiniuException) ex).response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }

    @Override
    public void test() {
        System.out.println("I M HERE!!!!!!!!!!!!!!!!!!!!");
    }

    public static void main(String[] args) {
        QiniuServiceImpl impl = new QiniuServiceImpl();
        String localFilePath = "/tmp/test.jpg";

        try {
            System.out.println(impl.upload(localFilePath));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}

