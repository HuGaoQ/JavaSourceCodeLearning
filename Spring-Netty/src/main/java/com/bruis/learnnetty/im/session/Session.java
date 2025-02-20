package com.bruis.learnnetty.im.session;

import lombok.Data;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/23
 */
@Data
public class Session {

    private String userId;

    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + "->" + userName;
    }
}