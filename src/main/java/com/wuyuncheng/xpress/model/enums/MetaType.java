package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum MetaType implements BaseEnum<String> {

    /**
     * 分类
     */
    CATEGORY("category", "分类"),

    /**
     * 标签
     */
    TAG("tag", "标签");

    private final String value;
    private final String description;

    MetaType(String value, String description) {
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
