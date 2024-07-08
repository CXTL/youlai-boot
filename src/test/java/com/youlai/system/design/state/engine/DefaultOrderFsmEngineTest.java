package com.youlai.system.design.state.engine;

import com.youlai.system.design.state.ServiceResult;
import com.youlai.system.design.state.context.CreateOrderContext;
import com.youlai.system.design.state.event.CreateEvent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class DefaultOrderFsmEngineTest {

    @Autowired
    private OrderFsmEngine orderFsmEngine;

    @Test
    void sendEvent() {
        CreateEvent createEvent = new CreateEvent();
        try {
            ServiceResult<String, CreateOrderContext> result = orderFsmEngine.sendEvent(createEvent);
            log.info("result=" + result);
        } catch (Exception e) {
            log.error("failed", e);
        }
    }

    @Test
    void testSendEvent() {
    }
}