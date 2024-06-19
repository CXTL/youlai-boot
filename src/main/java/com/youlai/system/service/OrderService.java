package com.youlai.system.service;

import com.youlai.system.model.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.system.model.form.OrderForm;
import com.youlai.system.model.query.OrderPageQuery;
import com.youlai.system.model.vo.OrderPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 订单表 服务类
 *
 * @author Ray Hao
 * @since 2024-06-19
 */
public interface OrderService extends IService<Order> {


    /**
     *订单表分页列表
     *
     * @return
     */
    IPage<OrderPageVO> listPagedOrders(OrderPageQuery queryParams);


    /**
     * 获取订单表表单数据
     *
     * @param id 订单表ID
     * @return
     */
     OrderForm getOrderFormData(Long id);


    /**
     * 新增订单表
     *
     * @param formData 订单表表单对象
     * @return
     */
    boolean saveOrder(OrderForm formData);

    /**
     * 修改订单表
     *
     * @param id   订单表ID
     * @param formData 订单表表单对象
     * @return
     */
    boolean updateOrder(Long id, OrderForm formData);


    /**
     * 删除订单表
     *
     * @param ids 订单表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteOrders(String ids);

}
