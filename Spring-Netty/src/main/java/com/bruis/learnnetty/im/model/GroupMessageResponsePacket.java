package com.bruis.learnnetty.im.model;

import com.bruis.learnnetty.im.session.Session;
import lombok.Getter;
import lombok.Setter;

import static com.bruis.learnnetty.im.model.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/24
 */
@Setter
@Getter
public class GroupMessageResponsePacket extends Packet {

    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}