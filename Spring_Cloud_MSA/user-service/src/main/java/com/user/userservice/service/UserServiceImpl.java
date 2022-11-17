package com.user.userservice.service;

import com.user.userservice.dto.UserDto;
import com.user.userservice.entity.UserEntity;
import com.user.userservice.repository.UserRepository;
import com.user.userservice.vo.ResponseOrder;
import com.user.userservice.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {

        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = mapper.map(userDto, UserEntity.class);
        userEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));

        userRepository.save(userEntity);

        UserDto returnUserDto = mapper.map(userEntity, UserDto.class);
        return returnUserDto;
    }


    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity =  userRepository.findByUserId(userId);

        if(userEntity==null){
            throw new UsernameNotFoundException("User not exist");
        }

        UserDto userDto = new ModelMapper().map(userEntity, UserDto.class);

        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);

        return userDto;
    }

    @Override
    public List<ResponseUser> getUserByAll() {
        List<ResponseUser> users = new ArrayList<>();

        List<UserEntity> userList = userRepository.findAll();

        userList.forEach(x -> {
            users.add(new ModelMapper().map(x, ResponseUser.class));
        });
        return users;
    }
}
