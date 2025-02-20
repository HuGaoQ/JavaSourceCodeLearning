package com.bruis.learnnetty.thread.synchronize;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟客户端请求类，用于构建请求对象
 *
 * @author lhy
 * @date 2022/2/10
 */
@Slf4j
@Data
public class RequestFuture {
    public static Map<Long, RequestFuture> futures = new ConcurrentHashMap<>();
    private long id;
    /**
     * 请求参数
     */
    private Object request;
    /**
     * 响应结果
     */
    private Object result;
    /**
     * 超时时间
     */
    private long timeout = 5000;

    /**
     * 把请求放入本地缓存中
     */
    public static void addFuture(RequestFuture future) {
        futures.put(future.getId(), future);
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
        synchronized (Objects.requireNonNull(future, "RequestFuture")) {
            future.notify();
        }
    }

    /**
     * 同步获取响应结果
     */
    public Object get() {
        synchronized (this) {
            while (this.result == null) {
                try {
                    // 主线程默认等待5s，然后查看下结果
                    this.wait(timeout);
                } catch (InterruptedException e) {
                    log.info("e: {}", e.getMessage());
                }
            }
        }
        return this.result;
    }
}
