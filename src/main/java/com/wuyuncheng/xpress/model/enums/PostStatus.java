package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum PostStatus implements BaseEnum<String> {

    /**
     * 已发布
     */
    PUBLISH("publish"),

    /**
     * 草稿
     */
    DRAFT("draft");

    private final String value;

    PostStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
