package com.usermanagement.mycompany.repository;

import com.usermanagement.mycompany.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
