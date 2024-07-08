package com.youlai.system.design.state;

import com.youlai.system.design.state.enums.OrderEventEnum;
import com.youlai.system.design.state.enums.OrderStateEnum;
import com.youlai.system.design.state.check.*;
import com.youlai.system.design.state.context.CreateOrderContext;
import com.youlai.system.design.state.context.StateContext;
import com.youlai.system.design.state.enums.ServiceType;
import com.youlai.system.design.state.event.CreateEvent;
import com.youlai.system.design.state.modole.OrderInfo;
import com.youlai.system.design.state.plugin.EstimatePricePluginHandler;
import com.youlai.system.design.state.plugin.PluginHandler;
import com.youlai.system.design.state.plugin.PluginHandlerable;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 订单创建处理器
 */
@Slf4j
@OrderProcessor(state = OrderStateEnum.INIT, bizCode = {"CHEAP", "POPULAR"}, sceneId = "H5", event = OrderEventEnum.CREATE)
public class StateCreateProcessor extends AbstractStateProcessor<String, CreateOrderContext>{

    @Resource
    private CreateParamChecker createParamChecker;
    @Resource
    private UserChecker userChecker;
    @Resource
    private UnfinshChecker unfinshChecker;
    @Resource
    private EstimatePricePluginHandler estimatePricePluginHandler;

    @Override
    public boolean filter(StateContext<CreateOrderContext> context) {
        OrderInfo orderInfo = (OrderInfo) context.getFsmOrder();
        context.setContext(new CreateOrderContext<>("estimatePriceInfo"));
        if (orderInfo.getServiceType() == ServiceType.TAKEOFF_CAR) {
            return true;
        }
        return false;
    }

    @Override
    public Checkable getCheckable(StateContext<CreateOrderContext> context) {
        return new Checkable() {
            @Override
            public List<Checker> getParamChecker() {
                return Arrays.asList(createParamChecker);
            }

            @Override
            public List<Checker> getSyncChecker() {
                return Collections.EMPTY_LIST;
            }

            @Override
            public List<Checker> getAsyncChecker() {
                return Arrays.asList(userChecker, unfinshChecker);
            }
        };
    }

    @Override
    public OrderStateEnum getNextState(StateContext<CreateOrderContext> context) {
        return OrderStateEnum.NEW;
    }

    @Override
    public ServiceResult<String, CreateOrderContext> action(String nextState, StateContext<CreateOrderContext> context) throws Exception {
        CreateEvent createEvent = (CreateEvent) context.getOrderStateEvent();

        // 促销信息信息
//        String price = priceSerive.getPrice();

        // 订单创建业务处理逻辑...

        ServiceResult<String, CreateOrderContext> result = new ServiceResult<>();
        result.setContext(context.getContext());
        result.setMsg("success");
        result.setSuccess(true);
        return result;
    }

    @Override
    public PluginHandlerable getPluginHandlerable(StateContext<CreateOrderContext> context) {
        return new PluginHandlerable() {
            @Override
            public List<PluginHandler> getSyncPluginHandler() {
                return Arrays.asList(estimatePricePluginHandler);
            }

            @Override
            public List<PluginHandler> getAsyncPluginHandler() {
                return Arrays.asList(estimatePricePluginHandler);
            }
        };
    }

    @Override
    public ServiceResult<String, CreateOrderContext> save(String nextState, StateContext<CreateOrderContext> context) throws Exception {
        OrderInfo orderInfo = (OrderInfo)context.getFsmOrder();
        // 更新状态
        orderInfo.setOrderState(nextState);
        // 持久化
//        this.updateOrderInfo(orderInfo);
        log.info("save BUSINESS order success, userId:{}, orderId:{}", orderInfo.getUserId(), orderInfo.getOrderId());
        return new ServiceResult<>(orderInfo.getOrderId(), context.getContext(), "business下单成功", true);
    }

    @Override
    public void after(StateContext<CreateOrderContext> context) {

    }
}
