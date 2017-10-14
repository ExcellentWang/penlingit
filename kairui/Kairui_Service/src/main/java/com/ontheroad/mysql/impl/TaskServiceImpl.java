package com.ontheroad.mysql.impl;

import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceLogMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceMapper;
import com.ontheroad.mysql.socketUtil.DeviceMessage;
import com.ontheroad.mysql.socketUtil.MinaServerHandler;
import com.ontheroad.pojo.TerminalDevice.DeviceLog;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.service.TaskService;
import org.apache.commons.lang.StringUtils;
import org.apache.mina.core.session.IoSession;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;
    @Autowired
    private DeviceLogMapper deviceLogMapper;

    @Scheduled(cron = "0 0 3 * * *")
    @Override
    public void syncTime() {
        DateTime now = new DateTime();

        ArrayList<String> args = new ArrayList<>();
        args.add(now.toString("YYYY"));
        args.add(now.toString("MM"));
        args.add(now.toString("dd"));
        args.add(now.toString("HH"));
        args.add(now.toString("mm"));
        args.add(now.toString("ss"));

        logger.info("SCHEDULE" + now.toString("YYYY-MM-dd HH:mm:ss"));
        for(IoSession session: MinaServerHandler.sessions) {
            // find device session
            String session_device_type = (String)session.getAttribute("device_type");
            String session_device_id = (String)session.getAttribute("device_id");
            if(StringUtils.isEmpty(session_device_id)) {
                continue;
            }
            DeviceMessage msg = new DeviceMessage(session_device_type, session_device_id, "xtds", args);
            session.write(msg.toString());
        }
    }

    @Scheduled(cron = "0 */3 * * * *")
    @Override
    public void checkOnline() {
    	logger.info("定时任务开始le-------------------");
        List<TerminalDevice> devices = deviceMapper.getAllDevices();
        Date now = Calendar.getInstance().getTime();
        for(TerminalDevice t: devices) {
            DeviceLog lastlog = deviceLogMapper.getLastDeviceLog(t.getEquipmentNum().split("LDCT01")[1]);
            if(lastlog == null || (now.getTime() - lastlog.getCreatedAt().getTime()) > 1000 * 180) {
                t.setWorkStatus(4);
                logger.info("------------------------------更新设备"+t.getEquipmentNum().split("LDCT01")[1]+"为离线状态");
            }else{
            	 t.setWorkStatus(0);
            }
            deviceMapper.updateDeviceWorkingStatus(t);
        }
        logger.info("定时任务结束-------------------");
    }

    @Override
    public void syncWeather() {

    }
  
}
