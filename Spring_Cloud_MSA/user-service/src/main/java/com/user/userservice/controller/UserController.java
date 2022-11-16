package com.user.userservice.controller;


import com.user.userservice.dto.UserDto;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.service.UserService;
import com.user.userservice.vo.Message;
import com.user.userservice.vo.RequestUser;
import com.user.userservice.vo.ResponseUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Api(tags = {" 사용자 정보를 관리하는 Controller"})
@RestController
@RequestMapping("/")
public class UserController {

    private Environment env;

    private UserService userService;
    
    @Autowired
    private Message message;

    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/health_check")
    public String status(){
        return "It's working in user-service";
    }

    @GetMapping("/welcome")
    public String welcome(){
        // 1번째 방법
        // return env.getProperty("testMessage");
        // 2번째 방법
        return message.getMessage();
    }

    @ApiOperation(value = "회원 등록 API")
    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = mapper.map(user,UserDto.class);
        userService.createUser(userDto);

        ResponseUser responseUser = mapper.map(userDto, ResponseUser.class);

       return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);

    }



}
