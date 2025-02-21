package com.bruis.springsecurity.utils.security;

import com.bruis.springsecurity.utils.JwtTokenUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LuoHaiYang
 */
public class SecurityUtils {

    public static JwtAuthenticationToken login(HttpServletRequest request, String username,
                                               String password, AuthenticationManager authenticationManager) {
        // 构造token
        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        // 通过authenticationManager
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功后将认证信息存储到SpringSecurity上下文中
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 将认证信息封装成令牌返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取当前登录信息
     */
    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取令牌进行认证
     */
    public static void checkAuthentication(HttpServletRequest request) {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationeFromToken(request);
        // 设置登录认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取当前用户名
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (!ObjectUtils.isEmpty(principal) && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取用户名
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (!ObjectUtils.isEmpty(principal) && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }
}
