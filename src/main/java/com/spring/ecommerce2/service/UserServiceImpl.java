package com.spring.ecommerce2.service;

import com.spring.ecommerce2.entity.User;
import com.spring.ecommerce2.exception.ItemAlreadyExistsException;
import com.spring.ecommerce2.exception.ResourceNotFoundException;
import com.spring.ecommerce2.model.UserModel;
import com.spring.ecommerce2.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepo;

    @Override
    public User readUser() {
        Long userId = getLoggedInUser().getId();
        return userRepo.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User not found for the id: " + userId));
    }

    @Override
    public User createUser(UserModel user) {
        if(userRepo.existsByEmail(user.getEmail())) {
            throw new ItemAlreadyExistsException("User is already registered with email: " + user.getEmail());
        }

        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));

        return userRepo.save(newUser);
    }

    @Override
    public User updateUser(UserModel user) {
        User existingUser = readUser();

        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAddress(user.getAddress() != null ? user.getAddress() : existingUser.getAddress());
        existingUser.setHp(user.getHp() != null ?  user.getHp() : existingUser.getHp());
        existingUser.setImages(user.getImages() != null ? user.getImages() : existingUser.getImages());

        return userRepo.save(existingUser);
    }

    @Override
    public void deleteUser() {
        User existingUser= readUser();

        userRepo.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        return userRepo.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found for the email: " + email));
    }
}