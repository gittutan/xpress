package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum MetaType implements BaseEnum<String> {

    /**
     * 分类
     */
    CATEGORY("category"),

    /**
     * 标签
     */
    TAG("tag");

    private final String value;

    MetaType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
