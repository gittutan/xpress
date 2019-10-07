package com.wuyuncheng.xpress.util;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class XPressUtils {

    private XPressUtils() {
    }

    private static class SingletonInstance {
        private static final MutableDataSet options = new MutableDataSet();
        private static final Parser parser = Parser.builder(options).build();
        private static final HtmlRenderer renderer = HtmlRenderer.builder(options).build();
    }

    public static boolean isBrowser() {
        HttpServletRequest request = XPressUtils.getCurrentRequest().get();
        String accept = request.getHeader(HttpHeaders.ACCEPT);
        return accept.contains("text/html");
    }

    public static Optional<HttpServletRequest> getCurrentRequest() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getRequest);
    }

    public static Optional<HttpServletResponse> getCurrentResponse() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(requestAttributes -> requestAttributes instanceof ServletRequestAttributes)
                .map(requestAttributes -> ((ServletRequestAttributes) requestAttributes))
                .map(ServletRequestAttributes::getResponse);
    }

    public static String markdownToHTML(String markdown) {
        Node document = SingletonInstance.parser.parse(markdown);
        return SingletonInstance.renderer.render(document);
    }

}
