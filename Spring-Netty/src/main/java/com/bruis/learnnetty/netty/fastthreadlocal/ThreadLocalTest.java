package com.bruis.learnnetty.netty.fastthreadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lhy
 * @date 2021/7/14
 */
@Slf4j
public class ThreadLocalTest {

    private static final ThreadLocal<Object> THREAD_LOCAL0 = new ThreadLocal<>();

    private static final ThreadLocal<Object> THREAD_LOCAL1 = new ThreadLocal<>();

    public static void main(String[] args) {
        // 线程外
        System.out.println("main线程1: " + THREAD_LOCAL0.get());
        Object o = new Object();
        THREAD_LOCAL0.set(o);

        new Thread(() -> {
            Object threadObject = THREAD_LOCAL0.get();
            System.out.println("线程内: " + threadObject);
            if (threadObject == null) {
                Object newObject = new Object();
                System.out.println("新new一个对象：" + newObject);
                THREAD_LOCAL0.set(newObject);
            }
            try {
                Thread.sleep(1000);
                System.out.println("休眠了一秒");
            } catch (Exception e) {
                log.info("e: {}", e.getMessage());
            }
            System.out.println("线程内，从ThreadLocal获取：" + THREAD_LOCAL0.get());
        }).start();
        System.out.println("main线程2: " + THREAD_LOCAL0.get());
    }
}
