package com.kairui.test;

import org.junit.Test;

public class UserServiceTest extends InterfaceBaseTest {

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Test
    public void testQrcode() {
        paramMap.put("method", "youle.vending.pay.qrcode");
        paramMap.put("payType", "1");
        paramMap.put("vendingMac", "00:e0:61:4c:29:8c");
        paramMap.put("goodsId", "1");
        paramMap.put("goodsNumber", "2");
    }

    @Test
    public void testStatus() {
        paramMap.put("method", "youle.vending.pay.status");
        paramMap.put("orderNo", "1201705311533186899835");
    }


}