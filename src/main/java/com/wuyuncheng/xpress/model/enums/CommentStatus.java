package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum  CommentStatus implements BaseEnum<String> {

    /**
     * 已通过
     */
    APPROVED("approved"),

    /**
     * 待审核
     */
    WAITING("waiting");

    private final String value;

    CommentStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
