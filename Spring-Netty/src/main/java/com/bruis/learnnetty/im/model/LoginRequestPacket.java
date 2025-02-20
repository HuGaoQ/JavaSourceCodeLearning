package com.bruis.learnnetty.im.model;

import lombok.Getter;
import lombok.Setter;

import static com.bruis.learnnetty.im.model.Command.LOGIN_REQUEST;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/22
 */
@Setter
@Getter
public class LoginRequestPacket extends Packet {

    private String userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }
}
