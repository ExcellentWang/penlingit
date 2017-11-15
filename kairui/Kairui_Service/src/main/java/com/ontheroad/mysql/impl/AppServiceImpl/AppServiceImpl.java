package com.ontheroad.mysql.impl.AppServiceImpl;

import com.ontheroad.mysql.Mapper.AppMapper.AppMapper;
import com.ontheroad.pojo.AppUpdate;
import com.ontheroad.pojo.Constant.BaseConstant;
import com.ontheroad.service.AppService.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class AppServiceImpl implements AppService {

    @Autowired
    AppMapper appMapper;

    @Override
    public Map<Object, Object> update() {
        //返回前端map
        Map<Object,Object> map = new HashMap<Object,Object>();
        try {
            AppUpdate appUpdate = appMapper.getUpdate();
            map.put("code", BaseConstant.appUserSuccessStatus);
            map.put("msg", "获取成功");
            map.put("extra",null);
            map.put("resultMap", appUpdate);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("code", BaseConstant.appUserErrorStatus);
            map.put("msg", "服务器异常");
            map.put("extra",null);
            map.put("resultMap", null);
            return map;
        }
    }
}
