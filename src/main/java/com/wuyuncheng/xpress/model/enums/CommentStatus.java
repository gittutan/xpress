package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum  CommentStatus implements BaseEnum<String> {

    /**
     * 已通过
     */
    APPROVE("approve", "已通过"),

    /**
     * 待审核
     */
    WAITING("waiting", "待审核");

    private final String value;
    private final String description;

    CommentStatus(String value, String description) {
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
