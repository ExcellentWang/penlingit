package com.kairui.test.app;

import com.kairui.test.InterfaceBaseTest;
import com.kairui.test.utils.URLCOMMON;
import org.junit.Test;

/**
 * Created by kedong on 2017/7/5 0005.
 */
public class CircleServiceTest extends InterfaceBaseTest {
    @Override
    public String getUrl() {
        return URLCOMMON.TEST.URL_HTTPS;
    }

    @Override
    public String getToken() {
        return "576deaff0053f2fd3ba91a6c2e8e8c7b";
    }


    @Test
    public void publish() {
        paramMap.put("method", "discover.circle.publish");
        paramMap.put("content", "今天天气很不错");
        paramMap.put("images", "https://www.baidu.com/img/baidu_jgylogo3.gif,https://www.baidu.com/img/baidu_jgylogo3.gif,https://www.baidu.com/img/baidu_jgylogo3.gif,https://www.baidu.com/img/baidu_jgylogo3.gif,https://www.baidu.com/img/baidu_jgylogo3.gif");
        paramMap.put("address", "武汉市南湖大道政苑小区");
        paramMap.put("lng", "114.3658974");
        paramMap.put("lat", "30.2315824");
    }

    @Test
    public void getUserCircleList() {
        paramMap.put("method", "discover.circle.usercirclelist");
        paramMap.put("targetId", "1");
    }

    @Test
    public void getCircleDetail() {
        paramMap.put("method", "discover.circle.detail");
        paramMap.put("circleId", "6");
    }

    @Test
    public void unLike(){
        paramMap.put("method", "discover.circle.unLike");
        paramMap.put("circleId", "6");
    }

    @Test
    public void like(){
        paramMap.put("method", "discover.circle.like");
        paramMap.put("circleId", "6");
    }

    @Test
    public void likeList(){
        paramMap.put("method", "discover.circle.like.list");
        paramMap.put("circleId", "6");
        paramMap.put("pageNum", "1");
    }

    @Test
    public void comment(){
        paramMap.put("method", "discover.circle.comment");
        paramMap.put("circleId", "6");
        paramMap.put("content", "还不错哦...");
    }

    @Test
    public void reply(){
        paramMap.put("method", "discover.circle.reply");
        paramMap.put("circleId", "6");
        paramMap.put("content", "666");
        paramMap.put("commentId", "1");
        paramMap.put("replyUserId", "2");
    }


    @Test
    public void commentList(){
        paramMap.put("method", "discover.circle.comment.list");
        paramMap.put("circleId", "6");
        paramMap.put("pageNum", "1");
    }
}
