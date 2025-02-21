package com.bruis.springsecurity.utils.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author LuoHaiYang
 */
@Data
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}