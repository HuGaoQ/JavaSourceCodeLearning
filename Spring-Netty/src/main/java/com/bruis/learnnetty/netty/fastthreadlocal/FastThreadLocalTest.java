package com.bruis.learnnetty.netty.fastthreadlocal;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * FastThreadLocal测试类
 *
 * @author lhy
 * @date 2021/7/13
 */
public class FastThreadLocalTest {

    /**
     * FastThreadLocal对象1
     */
    private static final FastThreadLocal<Object> THREAD_LOCAL0 = new FastThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            Object o = new Object();
            System.out.println("threadLocal0 initialValue: " + o);
            return o;
        }

        @Override
        protected void onRemoval(Object value) {
            System.out.println("onRemoval");
        }
    };

    private static final FastThreadLocal<Object> THREAD_LOCAL1 = new FastThreadLocal<Object>() {
        @Override
        protected Object initialValue() {
            Object o = new Object();
            System.out.println("threadLocal1 initialValue: " + o);
            return o;
        }
    };

    public static void main(String[] args) {
        new Thread(() -> {
            Object object0 = THREAD_LOCAL0.get();
            System.out.println(Thread.currentThread().getName() + "---> " + object0);

            THREAD_LOCAL0.set(new Object());
        }).start();

        new Thread(() -> {
            Object object0 = THREAD_LOCAL0.get();
            System.out.println(Thread.currentThread().getName() + "---> " + object0);

            System.out.println(Thread.currentThread().getName() + "---> " + (THREAD_LOCAL0.get() == object0));
//            while (true) {
//                System.out.println(Thread.currentThread().getName() + "---> " + (threadLocal0.get() == object0));
//                try {
//                    Thread.sleep(1000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
        }).start();
    }
}
