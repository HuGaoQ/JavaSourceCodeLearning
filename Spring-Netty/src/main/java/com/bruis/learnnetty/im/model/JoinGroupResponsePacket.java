package com.bruis.learnnetty.im.model;

import lombok.Getter;
import lombok.Setter;

import static com.bruis.learnnetty.im.model.Command.JOIN_GROUP_RESPONSE;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/24
 */
@Setter
@Getter
public class JoinGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
