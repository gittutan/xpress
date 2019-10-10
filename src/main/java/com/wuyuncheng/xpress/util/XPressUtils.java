package com.wuyuncheng.xpress.util;

import cn.hutool.extra.servlet.ServletUtil;
import com.vladsch.flexmark.ext.autolink.AutolinkExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.gfm.tasklist.TaskListExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class XPressUtils {

    private XPressUtils() {
    }

    private static class SingletonInstance {
        private static final MutableDataSet OPTIONS = new MutableDataSet()
                .set(Parser.EXTENSIONS, Arrays.asList(
                        TablesExtension.create(),
                        TaskListExtension.create(),
                        StrikethroughExtension.create(),
                        AutolinkExtension.create())).
                        set(HtmlRenderer.SOFT_BREAK, "<br />\n");
        private static final Parser PARSER = Parser.builder(OPTIONS).build();
        private static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).build();
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
        Node document = SingletonInstance.PARSER.parse(markdown);
        return SingletonInstance.RENDERER.render(document);
    }

    public static String getRequestIP() {
        return ServletUtil.getClientIP(getCurrentRequest().get());
    }

    public static String getRequestUserAgent() {
        HttpServletRequest request = getCurrentRequest().get();
        return request.getHeader("user-agent");
    }

}
