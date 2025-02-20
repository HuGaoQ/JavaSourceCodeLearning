package com.bruis.learnnetty.im.model;

import lombok.Getter;
import lombok.Setter;

import static com.bruis.learnnetty.im.model.Command.LOGIN_RESPONSE;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/22
 */
@Setter
@Getter
public class LoginResponsePacket extends Packet {

    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
