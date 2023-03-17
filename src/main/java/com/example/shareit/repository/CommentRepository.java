package com.example.shareit.repository;

import com.example.shareit.dto.CommentsDto;
import com.example.shareit.model.Comment;
import com.example.shareit.model.Post;
import com.example.shareit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findByUser(User user);
}
