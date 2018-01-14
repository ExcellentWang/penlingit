package com.ontheroad.mysql.impl.DeviceImpl;

import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceMapper;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * com.ontheroad.mysql.impl.DeviceImpl
 *
 * @author Wang
 * @create 2018-01-13 下午7:57
 * @description:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class DeviceImplTest {
    @Autowired
    private DeviceMapper deviceMapper;
    @Test
    public void getDeviceDetail() throws Exception {
        TerminalDevice deviceDetailById = deviceMapper.getDeviceDetailById(8);

        System.out.println("分割线");
        System.out.println(deviceDetailById.toString());
        System.out.println("分割线");

    }

}
/*
* TerminalDevice{equipment_id=8
* equipmentNum='LDCT01201704230003'
* equipmentName='null'
* type='01'
* equipmentPicture='null'
* workStatus=2
* voicebroadcast='null'
* volume='null'
* backlight='null'
* ip='null'
* port='null'
* status='null'
* effluent_way='0'
* effluent_type='null'
* effluent_type2='null'
* current_flow_grade='null'
* current_temp='67.6'
* settemperature='null'
* started='1'
* deviceShares=null
* appointment=null
* deviceUseLog=null
* customer_services=null
* guarantee=null
* guaranteeStatus='2'
* userTelephone='null'
* created_at=Sat Sep 09 16:47:17 CST 2017
* binded_at=Sat Sep 09 16:47:24 CST 2017
* last_active_at='null'
* firm_version=''
* m_use_water='null'
* m_jie_water='null'
* province='null'
* city='null'
* sur_water='null'
* sur_time='null'
* remark='null'
* m_send_time=null
* a_send_time=null
* rw_send_time=null
* cw_send_time=null
* scyc_send_time=null
* flowspeed='null'
* hotWaterTemp='null'
* codeWaterTemp='null'
* isProcessRepair=false
* product='null'
* period=3
* app_enabled='1'}

* */