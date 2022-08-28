package com.essoft.JavaSpringPracticum.repositories;

import com.essoft.JavaSpringPracticum.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(long productId);

    List<Comment> findByUserId(long userId);
}
