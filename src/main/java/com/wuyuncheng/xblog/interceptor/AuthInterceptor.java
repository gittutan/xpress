package com.wuyuncheng.xblog.interceptor;

import com.wuyuncheng.xblog.config.Constant;
import com.wuyuncheng.xblog.exception.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestToken = request.getHeader(Constant.HEADER_TOKEN);
        if (StringUtils.isEmpty(requestToken)) {
            throw new AuthException("身份认证失败");
        }
        Session session = sessionRepository.findById(requestToken);
        if (null == session) {
            throw new AuthException("身份认证失败");
        }
        return true;
    }

}
