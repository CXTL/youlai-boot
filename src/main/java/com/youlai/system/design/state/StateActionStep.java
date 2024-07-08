package com.youlai.system.design.state;

import com.youlai.system.design.state.enums.OrderStateEnum;
import com.youlai.system.design.state.check.Checkable;
import com.youlai.system.design.state.context.StateContext;
import com.youlai.system.design.state.plugin.PluginHandlerable;

/**
 * 状态机处理步骤
 * @param <T>
 * @param <C>
 */
public interface StateActionStep<T,C> {

    /**
     * 准备数据
     */
    default void prepare(StateContext<C> context) {
    }
    /**
     * 校验
     */
    Checkable getCheckable(StateContext<C> context);
    /**
     * 获取当前状态处理器处理完毕后，所处于的下一个状态
     */
    OrderStateEnum getNextState(StateContext<C> context);
    /**
     * 状态动作方法，主要状态迁移逻辑
     */
    ServiceResult<T,C> action(String nextState, StateContext<C> context) throws Exception;


    /**
     * 处理器的插件责任链
     */
    PluginHandlerable getPluginHandlerable(StateContext<C> context);

    /**
     * 状态数据持久化
     */
    ServiceResult<T,C> save(String nextState, StateContext<C> context) throws Exception;
    /**
     * 状态迁移成功，持久化后执行的后续处理
     */
    void after(StateContext<C> context);

}
