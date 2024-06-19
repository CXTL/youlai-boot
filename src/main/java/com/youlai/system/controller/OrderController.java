package com.youlai.system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.youlai.system.model.form.OrderForm;
import com.youlai.system.model.query.OrderPageQuery;
import com.youlai.system.model.vo.OrderPageVO;
import com.youlai.system.service.OrderService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youlai.system.common.result.PageResult;
import com.youlai.system.common.result.Result;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


/**
 * 订单表 前端控制器
 *
 * @author Ray Hao
 * @since 2024-06-19
 */
@Tag(name = "订单表接口")
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

        private final OrderService orderService;

        @Operation(summary = "订单表分页列表")
        @GetMapping("/page")
        public PageResult<OrderPageVO> listPagedOrders(OrderPageQuery queryParams ) {
            IPage<OrderPageVO> result = orderService.listPagedOrders(queryParams);
            return PageResult.success(result);
        }

        @Operation(summary = "新增订单表")
        @PostMapping
        public Result saveOrder(@RequestBody @Valid OrderForm formData ) {
            boolean result = orderService.saveOrder(formData);
            return Result.judge(result);
        }

        @Operation(summary = "订单表表单数据")
        @GetMapping("/{id}/form")
        public Result<OrderForm> getOrderForm(
            @Parameter(description = "订单表ID") @PathVariable Long id
        ) {
            OrderForm formData = orderService.getOrderFormData(id);
            return Result.success(formData);
        }

        @Operation(summary = "修改订单表")
        @PutMapping(value = "/{id}")
        public Result updateOrder(@Parameter(description = "订单表ID") @PathVariable Long id,
        @RequestBody @Validated OrderForm formData) {
            boolean result = orderService.updateOrder(id, formData);
            return Result.judge(result);
        }

        @Operation(summary = "删除订单表")
        @DeleteMapping("/{ids}")
        public Result deleteOrders(
            @Parameter(description = "订单表ID，多个以英文逗号(,)分割") @PathVariable String ids
        ) {
            boolean result = orderService.deleteOrders(ids);
            return Result.judge(result);
        }
}
