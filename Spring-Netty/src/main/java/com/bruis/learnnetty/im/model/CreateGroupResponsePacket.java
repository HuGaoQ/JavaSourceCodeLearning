package com.bruis.learnnetty.im.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.bruis.learnnetty.im.model.Command.CREATE_GROUP_RESPONSE;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/23
 */
@Setter
@Getter
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}