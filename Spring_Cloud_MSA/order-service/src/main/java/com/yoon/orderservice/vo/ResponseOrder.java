package com.yoon.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrder {
    @ApiModelProperty(value = "상품아이디", dataType = "String", required = true)
    private String productId;
    @ApiModelProperty(value = "주문수량", dataType = "String", required = true)
    private String qty;
    @ApiModelProperty(value = "전체가격", dataType = "Integer")
    private Integer totalPrice;
    @ApiModelProperty(value = "단일가격", dataType = "Integer", required = true)
    private Integer unitPrice;

    @ApiModelProperty(value = "주문일자", dataType = "Date")
    private Date createAt;

    @ApiModelProperty(value = "주문아이디", dataType = "String")
    private String orderId;
}
