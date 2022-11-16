package com.user.userservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {

    @ApiModelProperty(value = "유저 이메일", dataType = "String", required = true)
    private String email;
    @ApiModelProperty(value = "유저 이름", dataType = "String", required = true)
    private String name;
    @ApiModelProperty(value = "유저 아이디", dataType = "String", required = true)
    private String userId;

    @ApiModelProperty(value = "주문 데이터", dataType = "List<ResposeOrder>", required = true)
    private List<ResponseOrder> orders;
}
