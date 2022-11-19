package com.yoon.orderservice.service;


import com.netflix.servo.jmx.OrderedObjectNameMapper;
import com.yoon.orderservice.dto.OrderDto;
import com.yoon.orderservice.entity.OrderEntity;
import com.yoon.orderservice.repository.OrderRepository;
import com.yoon.orderservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        orderDto.setTotalPrice(orderDto.getQty()*orderDto.getUnitPrice());
        orderDto.setOrderId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        OrderEntity orderEntity = mapper.map(orderDto, OrderEntity.class);

        orderRepository.save(orderEntity);

        OrderDto returnUserDto = mapper.map(orderEntity, OrderDto.class);
        return returnUserDto;
    }

    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        OrderEntity orderEntity =  orderRepository.findByOrderId(orderId);

        OrderDto orderDto = new ModelMapper().map(orderEntity, OrderDto.class);

        return orderDto;
    }

    @Override
    public List<ResponseOrder> getAllOrders() {
        List<ResponseOrder> orders = new ArrayList<>();

        List<OrderEntity> orderEntity = orderRepository.findAll();
        orderEntity.forEach( x -> {
            orders.add(new ModelMapper().map(x, ResponseOrder.class));
        });

        return orders;
    }

    @Override
    public List<OrderDto> getOrdersByUserId(String userId) {
        List<OrderDto> orders = new ArrayList<>();

        List<OrderEntity> orderEntity =  orderRepository.findByUserId(userId);

        orderEntity.forEach( x -> {
            orders.add(new ModelMapper().map(x, OrderDto.class));
        });

        return orders;
    }
}
