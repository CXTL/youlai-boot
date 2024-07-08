package com.youlai.system.design.state.check;

import com.youlai.system.design.state.ServiceResult;
import com.youlai.system.design.state.context.StateContext;
import org.springframework.stereotype.Component;

@Component
public class UserChecker implements Checker{
    @Override
    public ServiceResult check(StateContext context) {
        ServiceResult<String,StateContext> result = new ServiceResult();
        //用户信息检查
        result.setContext(context);
        result.setMsg("success");
        result.setSuccess(true);
        return result;
    }
}
