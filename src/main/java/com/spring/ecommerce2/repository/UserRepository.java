package com.spring.ecommerce2.repository;

import com.spring.ecommerce2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}