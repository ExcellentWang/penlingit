package com.ontheroad.controller;

import com.alibaba.fastjson.JSON;
import com.danga.MemCached.MemCachedClient;
import com.google.gson.Gson;
import com.ontheroad.api.response.Response;
import com.ontheroad.core.exception.ValidateException;
import com.ontheroad.core.util.ConfigProperty;
import com.ontheroad.core.util.ParmeterSolver;
import com.ontheroad.core.util.SpringUtils;
import com.ontheroad.system.entity.ApiMethodModel;
import com.ontheroad.utils.Md5Util;
import com.ontheroad.utils.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.ontheroad.api.response.Response.MSG_API_NOT_EXIST;

@RestController
public class RouterController extends BaseController {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static final String METHOD_KEY = "method";
    private static final String SIGN_KEY = "sign";
    private static final String SECRET_KEY = "secretKey";
    private static final String TOKEN_KEY = "token";

    @Autowired
    private ParmeterSolver parmeterSolver;

    @Autowired
    private MemCachedClient memCachedClient;

    @RequestMapping(value = "/router", method = RequestMethod.POST)
    public Response router(HttpServletRequest request) {
        try {
            Map<String, String[]> parmaMap = request.getParameterMap();
            if (parmaMap.size() == 0) {
                return Response.ERROR("非法请求");
            }
            Map<String, String> parmeters = new HashMap<>();
            for (String key : parmaMap.keySet()) {
                parmeters.put(key, request.getParameter(key));
            }
            String methodName = parmeters.get(METHOD_KEY);
           // Gson gson = new Gson();
            logger.debug("-----接口《" + methodName + "》入参: " + JSON.toJSONString(parmeters));
            Object result = invoke(parmeters, request);
            logger.debug("-----接口《" + methodName + "》出参: " + JSON.toJSONString(result));
            return (Response) result;
        } catch (Exception e) {
            e.printStackTrace();
            Response response = Response.ERROR("服务器异常");
            if (e instanceof ValidateException) {
                response = Response.VALIDATE_ERROR();
                response.addErrDetailInfo(((ValidateException) e).getInfos());
            } else if (e instanceof NoSuchBeanDefinitionException) {
                response.setMsg(MSG_API_NOT_EXIST);
            }
            return response;
        }
    }

    private Object invoke(Map<String, String> parmeters, HttpServletRequest request) throws Exception {
        String original = parmeters.get(SIGN_KEY);
        if (StringUtils.isEmpty(original)) {
            return Response.FAILED("未签名的非法请求。");
        }
        // 验证签名
        if (!checkSign(parmeters)) {
            return Response.INVALID_SIGN();
        }

        String methodName = parmeters.get(METHOD_KEY);
        ApiMethodModel apiMethodModel = (ApiMethodModel) memCachedClient.get(methodName);
        if (apiMethodModel == null) {
            return Response.ERROR(MSG_API_NOT_EXIST);
        }
        // https
        if (apiMethodModel.getIsHttps()) {
            if (!request.isSecure()) {
                return Response.NOT_HTTPS();
            }
        }
        boolean needToken = apiMethodModel.getNeedToken();
        if (needToken) {
            String token = parmeters.get(TOKEN_KEY);
            // 将token替换成userId
            Integer userId = (Integer) memCachedClient.get(token);
            if (userId == null) {
                return Response.NOT_LOGIN();
            }
            parmeters.put("userId", userId + "");
        }
        Object object = SpringUtils.getBean(apiMethodModel.getBeanName());
        if (object == null) {
            return Response.ERROR(MSG_API_NOT_EXIST);
        }
        Class clazz = object.getClass();

        String parm = apiMethodModel.getParmetersName();
        if (StringUtils.isNotBlank(parm)) {
            // 接口存在参数
            String[] parms;
            if (parm.contains(",")) {
                parms = parm.split(",");
            } else {
                parms = new String[]{parm};
            }

            Class[] parmeterClass = new Class[parms.length];
            // service 参数对象
            Object[] args = new Object[parms.length];
            for (int i = 0; i < parms.length; i++) {
                Class pclazz = Class.forName(parms[i]);
                parmeterClass[i] = pclazz;
                // 设置参数对象的值
                Object obj = ObjectUtils.copyProperties(pclazz, parmeters);
                args[i] = obj;
            }
            // 获取接口方法
            Method method = ReflectionUtils.findMethod(clazz, apiMethodModel.getMethodName(), parmeterClass);
            if (method == null) {
                return Response.ERROR(MSG_API_NOT_EXIST);
            }
            // 接口参数校验
            parmeterSolver.validator(method, args);
            return ReflectionUtils.invokeMethod(method, object, args);
        } else {
            // 接口没有参数，直接执行
            // 获取接口方法
            Method method = ReflectionUtils.findMethod(clazz, methodName);
            if (method == null) {
                return Response.ERROR(MSG_API_NOT_EXIST);
            }
            return ReflectionUtils.invokeMethod(method, object);
        }
    }

    private Boolean checkSign(Map<String, String> parmeters) {
        String original = parmeters.get(SIGN_KEY);
        // 系统参数校验 sign
        TreeMap<String, String> treeMap = new TreeMap<>();
        if (parmeters != null) {
            treeMap.putAll(parmeters);
            treeMap.remove("sign");
        }

        String secretKey = ConfigProperty.getProperty(SECRET_KEY);
        String sign = Md5Util.md5Signature(treeMap, secretKey);
        logger.debug("-----服务器计算的sign值：" + sign);
        return sign.equals(original);
    }
}
