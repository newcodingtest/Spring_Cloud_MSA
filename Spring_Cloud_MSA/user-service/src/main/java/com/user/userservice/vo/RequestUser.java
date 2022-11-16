package com.user.userservice.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {

    @ApiModelProperty(value = "회원 아이디", dataType = "String", required = true)
    @NotNull(message="Email cannot be null")
    @Size(min = 2, message = "Email not be less than 2 characers")
    @Email
    private String email;

    @ApiModelProperty(value = "회원 이름", dataType = "String", required = true)
    @NotNull(message="Name cannot be null")
    @Size(min = 2, message = "Name not be less than 2 characers")
    private String name;

    @ApiModelProperty(value = "회원 비밀번호", dataType = "String", required = true)
    @NotNull(message="Name cannot be null")
    @Size(min = 8, message = "Password not be less than 8 characers")
    private String pwd;
}
