package com.ausafdev.student_inventory.Repository;

import com.ausafdev.student_inventory.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
