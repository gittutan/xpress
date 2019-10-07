package com.wuyuncheng.xpress.interceptor;

import com.wuyuncheng.xpress.config.XPressProperties;
import com.wuyuncheng.xpress.exception.AuthException;
import com.wuyuncheng.xpress.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private XPressProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws AuthException {
        String token = request.getHeader(properties.getJwtHeader());
        if (StringUtils.isEmpty(token)) {
            throw new AuthException("身份认证失败");
        }
        if (!JWTUtils.validateToken(token)) {
            throw new AuthException("身份认证失败");
        }
        return true;
    }

}
