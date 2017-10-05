package com.ontheroad.mysql.web.context;

/**
 * 缓存操作工厂类
 */
public class OscacheFactory {
    private static OscacheFactory instance;
    private static Object lock = new Object();
    private OscacheExtends oscacheExtends;

    private OscacheFactory() {
        oscacheExtends = new OscacheExtends("WFrameOScache", 3600 * 24 * 365);
    }

    public static OscacheFactory getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new OscacheFactory();
            }
        }
        return instance;
    }

    /**
     * 缓存一个对象
     *
     * @param key
     * @param value
     */
    public void putObject(String key, Object value) {
        oscacheExtends.put(key, value);
    }

    /**
     * 获取一个对象
     *
     * @param key
     * @return
     */
    public Object getObject(String key) {
        try {
            return oscacheExtends.get(key);
        } catch (Exception e) {
            //System.out.println("getObject(" + key + "):" + e.getMessage());
            return null;
        }
    }

    /**
     * 删除一个对象
     *
     * @param key
     */
    public void removeObject(String key) {
        oscacheExtends.remove(key);
    }

    /**
     * 删除所有对象
     */
    public void removeAllNews() {
        oscacheExtends.removeAll();
    }

}
