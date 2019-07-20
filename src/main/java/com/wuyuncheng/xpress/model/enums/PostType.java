package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum PostType implements BaseEnum<String> {

    /**
     * 文章
     */
    POST("post"),

    /**
     * 页面
     */
    PAGE("page");

    private final String value;

    PostType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
