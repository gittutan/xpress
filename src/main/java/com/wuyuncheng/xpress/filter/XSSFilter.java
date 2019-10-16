package com.wuyuncheng.xpress.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XSSFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        XSSHttpServletRequestWrapper XSSRequest = new XSSHttpServletRequestWrapper((HttpServletRequest) request);
        chain.doFilter(XSSRequest, response);
    }

}
