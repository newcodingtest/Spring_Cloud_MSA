package com.yoon.catalogservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCatalog {
    @ApiModelProperty(value = "아이디", dataType = "String", required = true)
    private String productId;
    @ApiModelProperty(value = "이름", dataType = "String", required = true)
    private String productName;
    @ApiModelProperty(value = "수량", dataType = "Integer", required = true)
    private Integer stock;
    @ApiModelProperty(value = "단일가격", dataType = "Integer", required = true)
    private Integer unitPrice;
    @ApiModelProperty(value = "등록일자", dataType = "Date", required = true)
    private Date createAt;
}
