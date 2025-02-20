package com.bruis.learnnetty.thread.synchronize;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 响应结果类
 *
 * @author lhy
 * @date 2022/2/10
 */
@Slf4j
@Data
public class Response {
    private long id;
    private Object result;
}
