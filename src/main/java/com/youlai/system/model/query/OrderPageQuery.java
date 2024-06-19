package com.youlai.system.model.query;

import com.youlai.system.common.base.BasePageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 订单表分页查询对象
 *
 * @author Ray Hao
 * @since 2024-06-19
 */
@Schema(description ="订单表分页查询对象")
@Data
public class OrderPageQuery extends BasePageQuery {

    @Schema(description="关键字")
    private String keywords;

}
