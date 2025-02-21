package com.bruis.springsecurity.dto;

import lombok.Data;

/**
 * @author LuoHaiYang
 */
@Data
public class User {
    private Long id;

    private String username;

    private String password;
}
