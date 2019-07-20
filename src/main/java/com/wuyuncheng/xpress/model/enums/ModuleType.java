package com.wuyuncheng.xpress.model.enums;

import com.wuyuncheng.xpress.model.enums.base.BaseEnum;

public enum ModuleType implements BaseEnum<String> {

    /**
     * html div 标签
     */
    DIV("div"),

    /**
     * html ul 标签
     */
    UL("ul");

    private final String value;

    ModuleType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}
