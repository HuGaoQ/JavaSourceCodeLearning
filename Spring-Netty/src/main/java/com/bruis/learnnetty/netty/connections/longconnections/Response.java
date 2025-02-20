package com.bruis.learnnetty.netty.connections.longconnections;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应结果类
 *
 * @author lhy
 * @date 2022/2/10
 */
@Setter
@Getter
public class Response {
    private long id;
    private Object result;
}
