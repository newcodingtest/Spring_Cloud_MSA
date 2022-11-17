package com.user.userservice.repository;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findUserByUserId(){
        String userId = "181cdd4e-ae72-408f-8179-9f16a368404c";
        log.info(userRepository.findByUserId(userId).getUserId());
    }
}
