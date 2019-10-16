package com.wuyuncheng.xpress.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.util.StringUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class XSSHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest originalRequest;
    /**
     * 配置可以通过过滤的白名单。
     */
    private static final Whitelist whitelist = new Whitelist();
    /**
     * 配置过滤化参数，不对代码进行格式化。
     */
    private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);

    public XSSHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        originalRequest = request;

    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(originalRequest.getInputStream()));
        String line = br.readLine();
        StringBuilder result = new StringBuilder();
        if (null != line) {
            result.append(clean(line));
        }
        return new WrappedServletInputStream(new ByteArrayInputStream(result.toString().getBytes()));
    }

    /**
     * 覆盖 getParameter 方法，将参数名和参数值都做 XSS 过滤。<br/>
     * 如果需要获得原始的值，则通过 super.getParameterValues(name) 来获取。<br/>
     * getParameterNames, getParameterValues 和 getParameterMap 也可能需要覆盖。<br/>
     */
    @Override
    public String getParameter(String name) {
        if (("content".equals(name) || name.endsWith("WithHtml"))) {
            return super.getParameter(name);
        }
        name = clean(name);
        String value = super.getParameter(name);
        if (!StringUtils.isEmpty(value)) {
            value = clean(value);
        }
        return value;
    }

    @Override
    public Map getParameterMap() {
        Map map = super.getParameterMap();
        Map<String, String> retMap = new HashMap<>();
        Iterator entries = map.entrySet().iterator();
        Map.Entry entry;
        String name;
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            retMap.put(name, clean(value).trim());
        }
        return retMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (null != arr) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = clean(arr[i]);
            }
        }
        return arr;
    }


    /**
     * 覆盖 getHeader 方法，将参数名和参数值都做 XSS 过滤。<br/>
     * 如果需要获得原始的值，则通过 super.getHeaders(name) 来获取。<br/>
     * getHeaderNames 也可能需要覆盖。
     */
    @Override
    public String getHeader(String name) {
        name = clean(name);
        String value = super.getHeader(name);
        if (!StringUtils.isEmpty(value)) {
            value = clean(value);
        }
        return value;
    }

    public String clean(String content) {
        String result = Jsoup.clean(content, "", whitelist, outputSettings);
        return result;
    }

    private class WrappedServletInputStream extends ServletInputStream {
        public void setStream(InputStream stream) {
            this.stream = stream;
        }

        private InputStream stream;

        public WrappedServletInputStream(InputStream stream) {
            this.stream = stream;
        }

        @Override
        public int read() throws IOException {
            return stream.read();
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
        }
    }

}
