package com.youlai.system.design.state.enums;

public enum OrderEventEnum {
    CREATE("创建"),
    ;

    OrderEventEnum(String state) {
    }

    @Override
    public String toString() {
        return this.name();
    }

}