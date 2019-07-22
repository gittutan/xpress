package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum PostType implements BaseEnum<String> {

    /**
     * 文章
     */
    POST("post", "文章"),

    /**
     * 页面
     */
    PAGE("page", "页面");

    private final String value;
    private final String description;

    PostType(String value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
