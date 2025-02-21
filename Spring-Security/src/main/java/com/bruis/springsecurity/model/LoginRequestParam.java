package com.bruis.springsecurity.model;

import lombok.Data;

/**
 * @author LuoHaiYang
 */
@Data
public class LoginRequestParam {

    private String username;

    private String password;
}
