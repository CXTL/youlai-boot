package com.youlai.system.design.state;

import com.youlai.system.design.state.context.StateContext;

/**
 * 状态机处理类
 */
public interface StateProcessor<T,C> {
    /**
     * 执行状态迁移的入口
     */
    ServiceResult<T,C>  action(StateContext<C> context) throws Exception;
}
