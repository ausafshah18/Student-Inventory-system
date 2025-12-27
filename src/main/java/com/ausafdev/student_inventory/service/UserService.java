package com.ausafdev.student_inventory.service;

import com.ausafdev.student_inventory.entity.User;

public interface UserService {
    User getUser(Long id);
    User getUser(String username);
    User saveUser(User user);
}
