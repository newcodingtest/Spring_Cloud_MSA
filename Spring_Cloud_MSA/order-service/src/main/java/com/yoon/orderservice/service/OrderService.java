package com.yoon.orderservice.service;

import com.yoon.orderservice.dto.OrderDto;
import com.yoon.orderservice.vo.ResponseOrder;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    List<ResponseOrder> getAllOrders();

    List<OrderDto> getOrdersByUserId(String userId);
}
