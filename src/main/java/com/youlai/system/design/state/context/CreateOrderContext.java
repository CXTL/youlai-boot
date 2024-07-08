package com.youlai.system.design.state.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateOrderContext<T> {
    private T estimatePriceInfo; //促销信息
}
