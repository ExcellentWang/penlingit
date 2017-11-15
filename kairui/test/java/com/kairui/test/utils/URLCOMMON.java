package com.kairui.test.utils;

public class URLCOMMON {
    private static final String PATH = "ontheroad/router";
//    private static final String PATH = "router";

    public static class LOCAL {
        public static String URL = "http://127.0.0.1:80/" + PATH;
        public static String URL_HTTPS = "https://127.0.0.1:443/" + PATH;
    }

    public static class TEST {
        public static String URL = "http://106.14.173.153/" + PATH;
        public static String URL_HTTPS = "https://106.14.173.153:8443/" + PATH;
    }
}
