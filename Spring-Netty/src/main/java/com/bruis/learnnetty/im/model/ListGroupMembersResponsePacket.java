package com.bruis.learnnetty.im.model;

import com.bruis.learnnetty.im.session.Session;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.bruis.learnnetty.im.model.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @Description
 * @Author luohaiyang
 * @Date 2022/3/24
 */
@Setter
@Getter
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}