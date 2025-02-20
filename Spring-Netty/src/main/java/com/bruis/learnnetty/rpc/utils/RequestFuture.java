package com.bruis.learnnetty.rpc.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟客户端请求类，用于构建请求对象
 *
 * @author lhy
 * @date 2022/2/10
 */
@Slf4j
@Getter
public class RequestFuture {
    public static Map<Long, RequestFuture> futures = new ConcurrentHashMap<>();
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    @Setter
    private long id;
    /**
     * 请求参数
     */
    @Setter
    private Object request;
    /**
     * 响应结果
     */
    @Setter
    private Object result;
    /**
     * 超时时间
     */
    @Setter
    private long timeout = 5000;
    /**
     * 请求路径
     */
    @Setter
    private String path;

    public static final AtomicLong AID = new AtomicLong();

    public RequestFuture() {
        id = AID.incrementAndGet();
        addFuture(this);
    }

    /**
     * 把请求放入本地缓存中
     */
    public static void addFuture(RequestFuture future) {
        futures.put(future.getId(), future);
    }

    /**
     * 同步获取响应结果
     */
    public Object get() {
        lock.lock();
        try {
            while (this.result == null) {
                try {
                    // 主线程默认等待5s，然后查看下结果
                    condition.await(timeout, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    log.info("e: {}", e.getMessage());
                }
            }
        } finally {
            lock.unlock();
        }
        return this.result;
    }

    /**
     * 异步线程将结果返回主线程
     */
    public static void received(Response result) {
        RequestFuture future = futures.remove(result.getId());
        if (null != future) {
            future.setResult(result.getResult());
        }
        /*
          通知主线程
         */
        Objects.requireNonNull(future, "RequestFuture").getLock().lock();
        try {
            future.getCondition().signalAll();
        } finally {
            Objects.requireNonNull(future, "RequestFuture").getLock().unlock();
        }
    }

}
