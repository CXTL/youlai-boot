package com.youlai.system.design.state;

import com.youlai.system.design.state.check.Checkable;
import com.youlai.system.design.state.check.CheckerExecutor;
import com.youlai.system.design.state.context.StateContext;
import com.youlai.system.design.state.plugin.PluginHandlerExecutor;
import com.youlai.system.design.state.plugin.PluginHandlerable;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 状态机处理器模板类
 */
@Component
public abstract class AbstractStateProcessor<T, C> implements StateProcessor<T, C>, StateActionStep<T, C> {

    @Resource
    private CheckerExecutor checkerExecutor;

    @Resource
    private PluginHandlerExecutor pluginHandlerExecutor;
    /**
     * 有可能根据 state+event+bizCode+sceneId 信息获取到的是多个状态处理器processor，
     * 有可能确实业务需要单纯依赖bizCode和sceneId两个属性无法有效识别和定位唯一processor，
     * 那么我们这里给业务开一个口、由业务决定从多个处理器中选一个适合当前上下文的，
     * 具体做法是业务processor通过filter方法根据当前context来判断是否符合调用条件。
     * 当然，如果最终经过业务filter之后，还是有多个状态处理器符合条件，那么这里只能抛异常处理了。这个需要在开发时，对状态和多维度处理器有详细规划。
     * 事务需要手动处理
     */
    public abstract boolean filter(StateContext<C> context);

    @Override
    public final ServiceResult<T,C> action(StateContext<C> context) throws Exception {
        ServiceResult<T,C> result = null;
        Checkable checkable = this.getCheckable(context);
        try {
            //参数校验器
            result = checkerExecutor.parallelCheck(checkable.getParamChecker(), context);
            if(!result.isSuccess()){
                return result;
            }
            // 数据准备
            this.prepare(context);
            // 串行校验器
            result = checkerExecutor.serialCheck(checkable.getSyncChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // 并行校验器
            result = checkerExecutor.parallelCheck(checkable.getAsyncChecker(), context);
            if (!result.isSuccess()) {
                return result;
            }
            // getNextState不能在prepare前，因为有的nextState是根据prepare中的数据转换而来
            String nextState = this.getNextState(context).name();
            // 业务逻辑
            result = this.action(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // 在action和save之间执行插件逻辑
//            PluginHandlerable pluginHandlerable = this.getPluginHandlerable(context);
//            result = this.pluginHandlerExecutor.parallelExecutor(pluginHandlerable.getAsyncPluginHandler(), context);
//            if (!result.isSuccess()) {
//                return result;
//            }
            // 持久化
            result = this.save(nextState, context);
            if (!result.isSuccess()) {
                return result;
            }
            // after
            this.after(context);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }
}
