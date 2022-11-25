package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {

}