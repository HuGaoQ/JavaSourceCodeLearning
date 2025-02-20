package com.bruis.learnnetty.im.model;

import lombok.Getter;
import lombok.Setter;

import static com.bruis.learnnetty.im.model.Command.MESSAGE_REQUEST;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/22
 */
@Setter
@Getter
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    public MessageRequestPacket(){}

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
