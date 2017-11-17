package com.ontheroad.mysql.socketUtil;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.danga.MemCached.MemCachedClient;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceErrorMapper;
import com.ontheroad.mysql.Mapper.DeviceMapper.DeviceMapper;
import com.ontheroad.mysql.dao.DeviceUseLogMapper;
import com.ontheroad.mysql.dao.DeviceWaterMapper;
import com.ontheroad.mysql.dao.TbEquipmentstatusMapper;
import com.ontheroad.mysql.entity.DeviceUseLog;
import com.ontheroad.mysql.entity.DeviceUseLogExample;
import com.ontheroad.mysql.entity.DeviceWater;
import com.ontheroad.mysql.entity.TbEquipmentstatus;
import com.ontheroad.mysql.entity.TbEquipmentstatusExample;
import com.ontheroad.pojo.TerminalDevice.DeviceError;
import com.ontheroad.pojo.TerminalDevice.DeviceLog;
import com.ontheroad.pojo.TerminalDevice.TerminalDevice;
import com.ontheroad.service.DeviceService.DeviceService;
import com.ontheroad.utils.HttpUtil;

@Component("DeviceMessageHandler")
public class DeviceMessageHandler {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceErrorMapper deviceErrorMapper;

    @Autowired
    private MemCachedClient memCachedClient;
    @Autowired
    private TbEquipmentstatusMapper tbEquipmentstatusMapper;
    @Autowired
    private DeviceUseLogMapper deviceUseLogMapper;
    @Autowired
    private DeviceWaterMapper deviceWaterMapper;

    private static final Logger logger = Logger.getLogger(DeviceMessageHandler.class);

	/**
	 * 
	 * 
	 * @Description: 接受硬件上传信息，解析硬件上传信息
	 * @param session
	 * @param msg
	 * @return void
	 * @throws @author
	 *             胡俊
	 * @email 510830970@qq.com
	 */
    public void recv(IoSession session, String msg) {
    	//存设备日志
        DeviceMessage deviceMessage = new DeviceMessage(msg);
        DeviceLog deviceLog = new DeviceLog();
        deviceLog.setRaw(msg);
        deviceLog.setDeviceID(deviceMessage.getDeviceID());
        session.setAttribute("device_type", deviceMessage.getDeviceType());
        session.setAttribute("device_id", deviceMessage.getDeviceID());
        session.setAttribute("equipmentNum", "LDCT" + deviceMessage.getDeviceType() + deviceMessage.getDeviceID());
        deviceLog.setDeviceType(deviceMessage.getDeviceType());
        deviceLog.setCommand(deviceMessage.getCommandType());
        String argsStr = StringUtils.join(deviceMessage.getArgs(), ",");
        deviceLog.setArgs(argsStr);
        deviceLog.setCreatedAt(Calendar.getInstance().getTime());

        InetSocketAddress addr = (InetSocketAddress) session.getRemoteAddress();
        deviceLog.setIp(addr.getAddress().getHostAddress());
        deviceLog.setPort(addr.getPort());
        deviceLog.setDirection("RECV");

        deviceService.insertDeviceLog(deviceLog);
        //处理设备上传内容
        handle(session, deviceMessage);
    }

    public void reply(IoSession session, DeviceMessage deviceMessage) {
        if (deviceMessage == null || deviceMessage.getDeviceID() == null) {
            return;
        }
        session.write(deviceMessage.toString());
    }
    /**
     * 
    * 
    * @Description: ，解析硬件上传信息
    * @param session
    * @param deviceMessage  
    * @return void   
    * @throws 
    * @author 胡俊
    * @email 510830970@qq.com
     */
    public void handle(IoSession session, DeviceMessage deviceMessage) {
        DeviceMessage rep;
        if (deviceMessage == null || deviceMessage.getDeviceID() == null) {
            return;
        }

        try {
            String num = "LDCT" + deviceMessage.getDeviceType() + deviceMessage.getDeviceID();
            String val;
            String val1;
            String val2;
            TerminalDevice device = deviceMapper.findDeviceByNum(num);
            InetSocketAddress addr = (InetSocketAddress) session.getRemoteAddress();
            device.setIp(addr.getAddress().getHostAddress());
            device.setPort(String.valueOf(addr.getPort()));
            //更新设备所在的地区
            String jsonr=GetProvinceCity(addr.getAddress().getHostAddress());
            if(jsonr!=null){
            	device.setProvince(JSON.parseObject(jsonr).getString("province"));
            	device.setCity(JSON.parseObject(jsonr).getString("city"));
            	deviceMapper.updateDevice(device);
            }
            List<String> ls=deviceMessage.getArgs();
            switch (deviceMessage.getCommandType()) {
                case "asdev": // 设备类型
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asdev",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    val = deviceMessage.getArgs().get(0);
                    device.setType(val);
                    deviceMapper.updateDevice(device);
                    logger.info("UPDATE " + device.getEquipment_id() + " DEV: " + val);
                    break;
                case "woty": // 出水方式
                    val = deviceMessage.getArgs().get(0);
                    logger.info("更新出水方式"+device.getEquipmentNum()+"val: "+val);
                    device.setEffluent_way(val);
                    deviceMapper.updateDevice(device);
                    break;
                case "wyuy": // 预约改变状态准备中
                    val = deviceMessage.getArgs().get(0);
                    logger.info("预约改变状态准备中"+device.getEquipmentNum()+"val: "+val);
                    device.setWorkStatus(1);
                    deviceMapper.updateDevice(device);
                    //更新或者插入预约表time
                    Date now = new Date();
                    Integer x= Integer.valueOf(ls.get(5))*100000;
                    deviceMapper.updateAppointmenTime(new Date(now .getTime() +x ));
                    break;
                case "asoty": // 出水方式主动上报
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asoty");
                    reply(session, rep);
                    val = deviceMessage.getArgs().get(0);
                    logger.info("主动上报:更新出水方式"+device.getEquipmentNum()+"val: "+val);
                    device.setEffluent_way(val);
                    deviceMapper.updateDevice(device);
                    break;
                case "womd": // 出水模式
                    val = deviceMessage.getArgs().get(0);
                    val1=deviceMessage.getArgs().get(1);//出水模式
                    val2=deviceMessage.getArgs().get(2);
                    if(val1.equals("01")){
                    	device.setSur_water(val2);//定量
                    }
                    if(val1.equals("02")){
                    	device.setSur_time(val2);//定时
                    }
                    logger.info("更新出水模式"+device.getEquipmentNum()+"val: "+val);
                    device.setCurrent_flow_grade(ls.get(3));
                    device.setEffluent_type(val);
                    device.setEffluent_type2(val1);
                    deviceMapper.updateDevice(device);
                    break;
                case "asomd": // 出水模式主动上报
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asomd",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    val = deviceMessage.getArgs().get(0);
                    val1=deviceMessage.getArgs().get(1);//出水模式
                    val2=deviceMessage.getArgs().get(2);
                    if(val1.equals("01")){
                    	device.setSur_water(val2);//定量
                    }
                    if(val1.equals("02")){
                    	device.setSur_time(val2);//定时
                    }
                    logger.info("主动上报:更新出水模式"+device.getEquipmentNum()+"val: "+val);
                    device.setEffluent_type(val);
                    device.setEffluent_type2(val1);
                    deviceMapper.updateDevice(device);
                    break;
                case "asdft": // 温度流量液晶
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asdft",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    val = deviceMessage.getArgs().get(0);
                    device.setSettemperature(val);
                    logger.info("更新设定温度"+device.getEquipment_id()+"val: "+val);
                    deviceMapper.updateDevice(device);
                    break;
                case "asbltr": // 背光
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asbltr",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    val = deviceMessage.getArgs().get(0);
                    TbEquipmentstatusExample example=new TbEquipmentstatusExample();
                    example.createCriteria().andEquipmentIdEqualTo(device.getEquipment_id());
                    TbEquipmentstatus tbEquipmentstatus=new TbEquipmentstatus();
                    tbEquipmentstatus.setEquipmentId(device.getEquipment_id());
                    tbEquipmentstatus.setBacklight(String.valueOf(Integer.parseInt(val)));
                    tbEquipmentstatusMapper.updateByExampleSelective(tbEquipmentstatus, example);
                    logger.info("液晶屏幕背光值" + device.getEquipment_id() + "背光值: " + val);
                    break;
                case "bltr": // 背光APP设置
                	 val = deviceMessage.getArgs().get(0);
                	 device.setBacklight(String.valueOf(Integer.parseInt(val)));
                	 logger.info("背光APP设置"+device.getEquipment_id()+"val: "+val);
                     deviceMapper.updateDevice(device);
                    break;
                case "asxzsj": // 洗澡时间
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asxzsj",
                            new ArrayList<>(Arrays.asList("1", "OK"))
                    );
                    reply(session, rep);
                    break;
                case "aspump": // 泵
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "aspump",
                            new ArrayList<>(Arrays.asList("1", "OK"))
                    );
                    reply(session, rep);
                    break;
                case "dsdl": // 定时定量
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "dsdl",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    break;
                case "scyc": // 上传异常
                	logger.info("app上传异常------------");
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "scyc",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);

                    DeviceError err = new DeviceError();
                    Calendar time = Calendar.getInstance();
                    Object[] args =  deviceMessage.getArgs().toArray();
                   /* if(!"0000".equals(args[0])) {
                        time.set(Integer.parseInt((String) args[0]),
                                Integer.parseInt((String) args[1]),
                                Integer.parseInt((String) args[2]),
                                Integer.parseInt((String) args[3]),
                                Integer.parseInt((String) args[4]),
                                Integer.parseInt((String) args[5]));

                        err.setUpdated_at(time.getTime());
                    } else {
                        err.setUpdated_at(Calendar.getInstance().getTime());
                    }*/
                    err.setUpdated_at(new Date());
                    err.setWater_tank(Integer.parseInt((String) args[6]));
                    err.setCold_water_in(Integer.parseInt((String) args[7]));
                    err.setHot_water_in(Integer.parseInt((String) args[8]));
                    err.setMixer_temp(Integer.parseInt((String) args[9]));
                    err.setMixer_comm(Integer.parseInt((String) args[10]));
                    err.setBuffer_in(Integer.parseInt((String) args[11]));
                    err.setWater_out(Integer.parseInt((String) args[12]));
                    err.setAc_power(Integer.parseInt((String) args[13]));
                    err.setBattery_volt(Integer.parseInt((String) args[14]));
                    err.setBattery_temp(Integer.parseInt((String) args[15]));
                    //设备id
                    err.setEquipment_id(device.getEquipment_id());
                    deviceErrorMapper.setDeviceError(err);
                    break;
                case "akgapp": // app禁用
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "akgapp",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    val=deviceMessage.getArgs().get(0);
                    device.setApp_enabled(val);
                    deviceMapper.updateDevice(device);
                    logger.info("app禁用---------val:"+val);
                    break;
                case "scjl": // 上传使用记录 （APP要数或者间隔时间到自动上传）
                    rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "scjl",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                    val = deviceMessage.getArgs().get(7);
                    logger.info("app上传使用记录---------val:"+val);
                    device.setWorkStatus(val.equals("00")?0:3);
                    deviceMapper.updateDevice(device);
                    break;
                case "verx": // 固件版本
                	String firmVersion=deviceMessage.getArgs().get(0);
                	 device.setFirm_version(firmVersion);
                     deviceMapper.updateDevice(device);
                     logger.info("--------------------固件版本被更新------- "+firmVersion);
                    break;
                case "real": // 实时数据
                	DeviceUseLog log=new DeviceUseLog();
                	log.setUploadstatus(ls.get(7));
                	log.setsWorkStatus(ls.get(6));
                	log.setUsetype(ls.get(8));
                	log.setTimeType(ls.get(9));
                	log.setSettemperature(ls.get(10));
                	log.setOuttemperature(ls.get(11));
                	log.setValveouttemperature(ls.get(12));
                	log.setBuffertemperature(ls.get(13));
                	log.setHotWaterTemp(ls.get(14));
                	log.setCodeWaterTemp(ls.get(15));
                	log.setFlowgrade(ls.get(16));
                	log.setFlowspeed(ls.get(17));
                	log.setBatteryvoltage(ls.get(18));
                	log.setBatterytemperature(ls.get(19));
                	log.setEquipmentId(device.getEquipment_id());
                	deviceUseLogMapper.insertSelective(log);
                	//更新当前温度和设定温度,工作状态workStatus
                	if(!"03".equals(ls.get(6))){
                		device.setWorkStatus(Integer.parseInt(ls.get(7))); //00：待机   01：准备中（包括预约倒计时） 02：使用中  03：离线
                	}else{
                		device.setWorkStatus(4);
                	}
                	device.setEffluent_type(ls.get(8));//出水模式更新
                	device.setSettemperature(ls.get(11));
                	device.setCurrent_temp(ls.get(12));
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------上传实时数据------- "+JSON.toJSONString(log));
                    break;
                case "scwt": //每次洗澡用水量节水量
                	DeviceWater de=new DeviceWater();
                	de.setDeviceId(Long.valueOf(device.getEquipment_id()));
                	de.setUseWater(ls.get(6));
                	de.setJieWater(ls.get(7));
                	de.setBathTime(ls.get(8));
                	de.setCreateTime(new Date());
                	deviceWaterMapper.insertSelective(de) ;
                	if(new Date().getDate()==1){//1号清空月用水量节水量
                		device.setM_jie_water("0");
                		device.setM_use_water("0");
                	}
                	device.setM_use_water(String.valueOf(Integer.parseInt(device.getM_use_water())+Integer.parseInt(ls.get(6))));
                	device.setM_jie_water(String.valueOf(Integer.parseInt(device.getM_jie_water())+Integer.parseInt(ls.get(7))));
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------上传每次洗澡用水量节水量------- ");
                    break;
                case "yyos": //语音播报开关，音量app设置
                	val = deviceMessage.getArgs().get(0);
                	device.setVoicebroadcast((Integer.parseInt(val)==0?"0":"1"));
                	device.setVolume(String.valueOf(Integer.parseInt(val)));
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------语音播报开关，音量app设置------- ");
                    break;
                case "asyyos": // 音量液晶屏设置
                	rep = new DeviceMessage(
                            deviceMessage.getDeviceType(),
                            deviceMessage.getDeviceID(),
                            "asyyos",
                            new ArrayList<>(Arrays.asList("OK"))
                    );
                    reply(session, rep);
                	val = deviceMessage.getArgs().get(0);
                	device.setVoicebroadcast((Integer.parseInt(val)==0?"0":"1"));
                	device.setVolume(String.valueOf(Integer.parseInt(val)));
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------音量液晶屏设置------- "+("0".equals(val)?"2":"1"));
                    break;
                case "wdft": //设定温度
                	val = deviceMessage.getArgs().get(0);
                	device.setCurrent_temp(val);
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------设定温度------- ");
                    break;
                case "xtpc": //心跳
                	if(device.getWorkStatus()==4){
                		device.setWorkStatus(0);
                	}
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------心跳------- ");
                    break;
                case "daij": //待机
                	device.setWorkStatus(0);
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------待机------- ");
                    break;
                case "kszt": // 开始暂停控制
                	val = deviceMessage.getArgs().get(0);
                	device.setStarted(val);
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------开始暂停控制------- "+val);
                    break;
                case "askazt": // 开始暂停主动上报
                	val = deviceMessage.getArgs().get(0);
                	device.setStarted("0".equals(val)?"2":"1");
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------开始暂停控制------- "+("0".equals(val)?"2":"1"));
                    break;
                case "init": // 设备初始化
                	device.setType(ls.get(0));
                	device.setEffluent_way(ls.get(1));
                	device.setEffluent_type(ls.get(2));
                	device.setEffluent_type2(ls.get(3));
                	 if(ls.get(3).equals("01")){
                     	device.setSur_water(ls.get(4));//定量
                     }
                     if(ls.get(3).equals("02")){
                     	device.setSur_time(ls.get(4));//定时
                     }
                	device.setSettemperature(ls.get(5));
                	device.setCurrent_flow_grade(ls.get(6));
                	//心跳间隔
                	//使用记录间隔
                	device.setApp_enabled(ls.get(9));
                	deviceMapper.updateDevice(device);
                    logger.info("--------------------设备初始化------- ");
                    break;
              
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
    	System.out.println(Integer.parseInt("00"));
	}
    public static String GetProvinceCity(String ip){
    	try {
			ip="format=js&ip="+ip;
			String str=HttpUtil.sendGet("http://int.dpool.sina.com.cn/iplookup/iplookup.php",ip);
			String json=str.split("=")[1].split(";")[0];
			return json;
		} catch (Exception e) {
			logger.error("--------------------------通过ip获取地区失败-----------------------");
			return null;
		}
    }
}
