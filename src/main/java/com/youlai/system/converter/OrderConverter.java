package com.youlai.system.converter;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.youlai.system.model.dto.OrderDTO;
import com.youlai.system.model.entity.Order;
import com.youlai.system.model.vo.OrderPageVO;
import com.youlai.system.model.form.OrderForm;
import com.youlai.system.model.bo.OrderBO;

/**
 * 订单表转换器
 *
 * @author Ray Hao
 * @since 2024-06-19
 */
@Mapper(componentModel = "spring")
public interface OrderConverter{

    OrderPageVO bo2PageVo(OrderBO bo);

    Page<OrderPageVO> bo2PageVo(Page<OrderBO> bo);

    OrderForm entity2Form(Order entity);

    @InheritInverseConfiguration(name = "entity2Form")
    Order form2Entity(OrderForm entity);
}