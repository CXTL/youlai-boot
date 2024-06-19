package com.youlai.system.service.impl;

import com.youlai.system.model.entity.Order;
import com.youlai.system.mapper.OrderMapper;
import com.youlai.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.youlai.system.common.util.DateUtils;
import com.youlai.system.model.form.OrderForm;
import com.youlai.system.model.query.OrderPageQuery;
import com.youlai.system.model.bo.OrderBO;
import com.youlai.system.model.vo.OrderPageVO;
import com.youlai.system.converter.OrderConverter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;

/**
 * 订单表服务实现类
 *
 * @author Ray Hao
 * @since 2024-06-19
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private final OrderConverter orderConverter;

    /**
    * 获取订单表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<OrderPageVO>} 订单表分页列表
    */
    @Override
    public IPage<OrderPageVO> listPagedOrders(OrderPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<OrderBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<OrderBO> boPage = this.baseMapper.listPagedOrders(page, queryParams);
    
        // 实体转换
        return orderConverter.bo2PageVo(boPage);
    }
    
    /**
     * 获取订单表表单数据
     *
     * @param id 订单表ID
     * @return
     */
    @Override
    public OrderForm getOrderFormData(Long id) {
        Order entity = this.getById(id);
        return orderConverter.entity2Form(entity);
    }
    
    /**
     * 新增订单表
     *
     * @param formData 订单表表单对象
     * @return
     */
    @Override
    public boolean saveOrder(OrderForm formData) {
        // 实体转换 form->entity
        Order entity = orderConverter.form2Entity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新订单表
     *
     * @param id   订单表ID
     * @param formData 订单表表单对象
     * @return
     */
    @Override
    public boolean updateOrder(Long id,OrderForm formData) {
        Order entity = orderConverter.form2Entity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除订单表
     *
     * @param ids 订单表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteOrders(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
