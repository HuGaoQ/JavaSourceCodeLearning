package com.bruis.learnnetty.rpc.utils;

import lombok.Data;

/**
 * 响应结果类
 *
 * @author lhy
 * @date 2022/2/10
 */
@Data
public class Response {
    private long id;
    private Object result;
}
