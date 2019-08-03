package com.niaobulashi.framework.manager;

import com.niaobulashi.common.utils.ThreadsUtils;
import com.niaobulashi.common.utils.spring.SpringUtils;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: niaobulashi-framework
 * @description: 异步任务管理器
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 10:40
 */
public class AsyncManager {

    /**
     * 操作延迟10毫秒
     */
    private final int OPERATE_DELAY_TIME = 10;

    /**
     * 异步操作任务调度线程池
     */
    private ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");

    /**
     * 单例线程
     */
    private AsyncManager() {};

    private static AsyncManager me = new AsyncManager();

    public static AsyncManager me() {
        return me;
    }

    /**
     * 执行任务
     * @param task
     */
    public void execute(TimerTask task) {
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        ThreadsUtils.shutdownAndAwaitTermination(executor);
    }
}