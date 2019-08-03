package com.niaobulashi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * @program: niaobulashi-common
 * @description: 线程相关工具类
 * @author: 鸟不拉屎 https://niaobulashi.com
 * @create: 2019-08-03 10:41
 */
public class ThreadsUtils {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsUtils.class);

    /**
     * sleep睡眠等待，单位毫秒
     * @param milliseconds
     */
    public static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * 停止线程池
     * 先使用shutdown，停止接收新任务并尝试完成所有已存在的任务
     * 如果超时，则调用shutdownNow，取消在workQueue中Pending的任务，并中断所有阻塞函数
     * 如果仍有超时，则强制退出
     * 另外在shutdown时线程本身被调用中断做了处理
     * @param pool
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                        logger.info("Pool did not terminate");
                    }
                }
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程异常信息
     * @param r
     * @param t
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            }catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
        if (t != null) {
            logger.error(t.getMessage(), t);
        }
    }
}
