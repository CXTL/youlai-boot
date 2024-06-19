package com.youlai.system.mapper;

import com.youlai.system.model.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youlai.system.model.bo.OrderBO;
import com.youlai.system.model.query.OrderPageQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单表 Mapper 接口
 *
 * @author Ray Hao
 * @since 2024-06-19
 */

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<OrderBO> listPagedOrders(Page<OrderBO> page, OrderPageQuery queryParams);

}
