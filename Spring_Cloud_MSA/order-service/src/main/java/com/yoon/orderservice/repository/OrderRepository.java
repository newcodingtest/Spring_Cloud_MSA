package com.yoon.orderservice.repository;

import com.yoon.orderservice.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    OrderEntity findByOrderId(String orderId);

    List<OrderEntity> findByUserId(String userId);

}
