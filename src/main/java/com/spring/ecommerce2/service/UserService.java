package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.User;
import com.spring.ecommerce2.model.UserModel;

public interface UserService {

    User readUser(Long id);

    User createUser(UserModel user);

    User updateUser(UserModel user, Long id);

    void deleteUser(Long id);
}