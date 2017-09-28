package com.ontheroad.core.util;


import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by kedong on 2016/12/19.
 * 线程池
 */
public class ThreadUtil {

    private static ThreadPoolExecutor threadPoolExecutor;

    /**
     * 初始化时线程池大小为 60
     * @return
     */
    public static ThreadPoolExecutor getThreadPool() {
        if (threadPoolExecutor == null) {
            synchronized (ThreadUtil.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = new ThreadPoolExecutor(0, 60, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                }
            }
        }
        return threadPoolExecutor;
    }


}
