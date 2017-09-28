package com.ontheroad.path.model;

import org.mybatis.annotation.Table;

import java.io.Serializable;


/**
 * PathUser对象定义
 * <p>
 * 工具自动生成代码
 *
 * @author Admin
 */
@Table(pkId = {"id"}, tabName = "path_user")
public class PathUserModel implements Serializable {

    private static final long serialVersionUID = 5076501529453453070L;
    /**  */
    private Integer id;
    /**  */
    private Integer userid;
    /**  */
    private Integer sportid;
    private Integer type;
    /**
     * 运动轨迹坐标点（以数组的形式存放）
     * 数组的首尾即为轨迹的开始坐标和结束坐标
     */
    private String points;
    /**
     * 开始位置(名称)
     */
    private String start;
    /**
     * 结束位置(名称)
     */
    private String end;
    /**
     * 轨迹别名
     */
    private String name;
    /**
     * 该轨迹的长度(公里）
     */
    private String kilometer;
    /**
     * 时长
     */
    private Double time;
    /**
     * 最高海拔
     */
    private Double altitude;
    /**
     * 累计爬升高度
     */
    private Double height;
    /**
     * 平均速度
     */
    private Double speed;
    /**
     * 轨迹缩略图
     */
    private String pathIconUrl;

    /**
     * 取得
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 取得
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 取得
     */
    public Integer getSportid() {
        return sportid;
    }

    /**
     * 设置
     */
    public void setSportid(Integer sportid) {
        this.sportid = sportid;
    }

    /**
     * 取得运动轨迹坐标点（以数组的形式存放）
     * 数组的首尾即为轨迹的开始坐标和结束坐标
     */
    public String getPoints() {
        return points;
    }

    /**
     * 设置运动轨迹坐标点（以数组的形式存放）
     * 数组的首尾即为轨迹的开始坐标和结束坐标
     */
    public void setPoints(String points) {
        this.points = points;
    }

    /**
     * 取得开始位置(名称)
     */
    public String getStart() {
        return start;
    }

    /**
     * 设置开始位置(名称)
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * 取得结束位置(名称)
     */
    public String getEnd() {
        return end;
    }

    /**
     * 设置结束位置(名称)
     */
    public void setEnd(String end) {
        this.end = end;
    }

    /**
     * 取得轨迹别名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置轨迹别名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得该轨迹的长度(公里）
     */
    public String getKilometer() {
        return kilometer;
    }

    /**
     * 设置该轨迹的长度(公里）
     */
    public void setKilometer(String kilometer) {
        this.kilometer = kilometer;
    }

    /**
     * 取得时长
     */
    public Double getTime() {
        return time;
    }

    /**
     * 设置时长
     */
    public void setTime(Double time) {
        this.time = time;
    }

    /**
     * 取得最高海拔
     */
    public Double getAltitude() {
        return altitude;
    }

    /**
     * 设置最高海拔
     */
    public void setAltitude(Double altitude) {
        this.altitude = altitude;
    }

    /**
     * 取得累计爬升高度
     */
    public Double getHeight() {
        return height;
    }

    /**
     * 设置累计爬升高度
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * 取得平均速度
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * 设置平均速度
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPathIconUrl() {
        return pathIconUrl;
    }

    public void setPathIconUrl(String pathIconUrl) {
        this.pathIconUrl = pathIconUrl;
    }
}
