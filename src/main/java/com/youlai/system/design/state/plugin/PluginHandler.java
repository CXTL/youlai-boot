package com.youlai.system.design.state.plugin;

import com.youlai.system.design.state.ServiceResult;
import com.youlai.system.design.state.StateProcessor;
import com.youlai.system.design.state.context.StateContext;

/**
 * 插件处理器
 *
 * @param <T>
 * @param <C>
 */
public interface PluginHandler<T, C> extends StateProcessor<T, C> {
    /**
     * 多个 pluginHandler 时的执行顺序
     */
    default int order() {
        return 0;
    }

    /**
     * 是否需求release
     */
    default boolean needRelease() {
        return false;
    }

    /**
     * 业务执行完成后的释放方法,
     * 比如有些业务会在pluginHandler中加一些状态操作，等业务执行完成后根据结果选择处理这些状态操作,
     * 最典型的就是pluginHandler中加一把锁，release根据结果释放锁.
     */
    default void release(StateContext<C> context, ServiceResult<T, C> result) {

    }
}
