package com.youlai.system.design.state.plugin;

import com.youlai.system.design.state.ServiceResult;
import com.youlai.system.design.state.context.CreateOrderContext;
import com.youlai.system.design.state.context.StateContext;
import com.youlai.system.design.state.enums.OrderEventEnum;
import com.youlai.system.design.state.enums.OrderStateEnum;
import org.springframework.stereotype.Component;

@ProcessorPlugin(state = OrderStateEnum.INIT, event = OrderEventEnum.CREATE, bizCode = "BUSINESS")
@Component
public class EstimatePricePluginHandler implements PluginHandler<String, CreateOrderContext> {

    @Override
    public ServiceResult action(StateContext<CreateOrderContext> context) throws Exception {
//        String price = priceSerive.getPrice();
        String price = "";
        context.getContext().setEstimatePriceInfo(price);
        return new ServiceResult();
    }

}