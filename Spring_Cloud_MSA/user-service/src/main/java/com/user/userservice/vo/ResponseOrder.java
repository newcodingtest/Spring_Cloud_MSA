package com.user.userservice.vo;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResponseOrder {
    @ApiModelProperty(value = "상품 번호", dataType = "String", required = true)
    private String productId;
    @ApiModelProperty(value = "주문 수량", dataType = "Integer", required = true)
    private Integer qty;
    @ApiModelProperty(value = "단일 가격", dataType = "Integer", required = true)
    private Integer unitPrice;
    @ApiModelProperty(value = "전체 가격", dataType = "Integer", required = true)
    private Integer totalPrice;
    @ApiModelProperty(value = "주문 일자", dataType = "Date", required = true)
    private Date createAt;

    @ApiModelProperty(value = "주문 아이디", dataType = "String", required = true)
    private String orderId;
}
