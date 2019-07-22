package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum PostStatus implements BaseEnum<String> {

    /**
     * 已发布
     */
    PUBLISH("publish", "已发布"),

    /**
     * 草稿
     */
    DRAFT("draft", "草稿");

    private final String value;
    private final String description;

    PostStatus(String value, String description) {
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
