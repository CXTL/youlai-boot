package com.youlai.system.design.state;

import com.youlai.system.design.state.enums.OrderStateEnum;
import com.youlai.system.design.state.enums.ServiceType;
import com.youlai.system.design.state.modole.FsmOrder;
import com.youlai.system.design.state.modole.OrderInfo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FsmOrderService {
    public FsmOrder getFsmOrder(String orderId) {
        return new OrderInfo(UUID.randomUUID().toString(), OrderStateEnum.INIT.toString(), "POPULAR", "H5", "root", ServiceType.TAKEOFF_CAR);
    }
}
