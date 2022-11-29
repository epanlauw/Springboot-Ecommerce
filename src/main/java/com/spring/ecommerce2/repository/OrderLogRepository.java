package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.OrderLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLogRepository extends JpaRepository<OrderLog, Long> {

}