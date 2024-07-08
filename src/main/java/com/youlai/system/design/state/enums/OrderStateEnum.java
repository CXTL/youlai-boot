package com.youlai.system.design.state.enums;

public enum OrderStateEnum {
    INIT("初始化"),
    NEW("新建"),
    ;

    OrderStateEnum(String state) {
    }

    @Override
    public String toString() {
        return this.name();
    }
}
