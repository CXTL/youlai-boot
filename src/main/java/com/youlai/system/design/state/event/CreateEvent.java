package com.youlai.system.design.state.event;

import com.youlai.system.design.state.enums.OrderEventEnum;

import java.util.UUID;

public class CreateEvent implements OrderStateEvent{
    @Override
    public String getEventType() {
        return OrderEventEnum.CREATE.toString();
    }

    @Override
    public String getOrderId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public boolean newCreate() {
        return true;
    }
}
