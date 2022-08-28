package com.essoft.JavaSpringPracticum.repositories;

import com.essoft.JavaSpringPracticum.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
