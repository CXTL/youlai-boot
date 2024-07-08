package com.youlai.system.design.state.modole;

/**
 * 状态机订单的基础信息
 */
public interface FsmOrder {
    /**
     * 订单ID
     */
    String getOrderId();

    /**
     * 订单状态
     */
    String getOrderState();

    /**
     * 订单的业务属性
     */
    String bizCode();

    /**
     * 订单的场景属性
     */
    String sceneId();
}
