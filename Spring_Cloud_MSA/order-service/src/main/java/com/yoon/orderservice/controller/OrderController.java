package com.yoon.orderservice.controller;


import com.yoon.orderservice.dto.OrderDto;
import com.yoon.orderservice.service.OrderService;
import com.yoon.orderservice.vo.RequestOrder;
import com.yoon.orderservice.vo.ResponseOrder;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order-service")
public class OrderController {
    private final Environment env;
    private final OrderService orderService;

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's working in catalog-service on Port %s",env.getProperty("local.server.port"));
    }

    @ApiOperation(value = "주문하기")
    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createOrder(@PathVariable("userId")String userId,
                                                     @RequestBody RequestOrder requestOrder){

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        OrderDto orderDto = mapper.map(requestOrder, OrderDto.class);
        orderDto.setUserId(userId);
        OrderDto createdOrder = orderService.createOrder(orderDto);
        ResponseOrder responseOrder = mapper.map(createdOrder, ResponseOrder.class);

        return  ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }




    @ApiOperation(value = "상품들 조회")
    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId")String userId){

        List<OrderDto> orders = orderService.getOrdersByUserId(userId);

        List<ResponseOrder> responseOrders = new ArrayList<>();
        orders.forEach(x -> {
            responseOrders.add(new ModelMapper().map(x, ResponseOrder.class));
        });

        return ResponseEntity.status(HttpStatus.OK).body(responseOrders);
    }
}
