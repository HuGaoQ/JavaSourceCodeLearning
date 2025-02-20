package com.bruis.learnnetty.im.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.bruis.learnnetty.im.model.Command.CREATE_GROUP_REQUEST;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/23
 */
@Setter
@Getter
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
