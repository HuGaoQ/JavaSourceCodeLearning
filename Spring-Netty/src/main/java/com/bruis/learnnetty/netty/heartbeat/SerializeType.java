package com.bruis.learnnetty.netty.heartbeat;

import lombok.Getter;

/**
 * @author lhy
 * @date 2021/8/20
 */
@Getter
public enum SerializeType {
    JSON((byte) 0);

    private final byte code;

    SerializeType(byte code) {
        this.code = code;
    }

    public static SerializeType valueOf(byte code) {
        for (SerializeType serializeType : SerializeType.values()) {
            if (serializeType.getCode() == code) {
                return serializeType;
            }
        }
        return null;
    }

}
