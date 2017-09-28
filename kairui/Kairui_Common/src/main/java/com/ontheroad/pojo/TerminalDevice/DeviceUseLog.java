package com.ontheroad.pojo.TerminalDevice;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class DeviceUseLog implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter @Setter private int log_id;
    @Getter @Setter private int equipment_id = 1; // 设备ID

    @Getter @Setter private String uploadStatus = "1"; // 上传的数据状态，01，预热开始洗浴 02，改变设定温度  03 改变设定
    @Getter @Setter private Date useTime = Calendar.getInstance().getTime(); // 使用时间
    @Getter @Setter private String useType = "01"; // 模式，01，成人模式 02，儿童模式
    @Getter @Setter private String useDuration = "30"; // 使用时长
    @Getter @Setter private String useWeight = "100"; // 用水量
    @Getter @Setter private String saveWeight = "20";// 节水量

    @Getter @Setter private String valveOutTemperature = "38"; //混水阀出水温度
    @Getter @Setter private String setTemperature = "38"; //设定温度
    @Getter @Setter private String bufferTemperature = "40"; //缓冲温度
    @Getter @Setter private String outTemperature = "38";// 出水温度

    @Getter @Setter private String flowGrade = "001"; //流量等级
    @Getter @Setter private String flowSpeed = "060"; //流量等级对应的流速单位L/min

    @Getter @Setter private String hotTemperature = "50"; // 热水温度
    @Getter @Setter private String coldTemperature = "10"; // 冷水温度
    @Getter @Setter private int app_enabled = 1; // app 是否启用

}
