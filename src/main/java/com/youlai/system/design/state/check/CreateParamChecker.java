package com.youlai.system.design.state.check;

import com.youlai.system.design.state.ServiceResult;
import com.youlai.system.design.state.context.StateContext;
import org.springframework.stereotype.Component;

@Component
public class CreateParamChecker implements Checker{
    @Override
    public ServiceResult check(StateContext context) {
        ServiceResult<String,StateContext> result = new ServiceResult();
        result.setContext(context);
        result.setMsg("success");
        result.setSuccess(true);
        return result;
    }
}
