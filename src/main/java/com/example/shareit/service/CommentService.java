package com.example.shareit.service;

import com.example.shareit.dto.CommentsDto;
import com.example.shareit.exceptions.PostNotFountException;
import com.example.shareit.model.Post;
import com.example.shareit.repository.PostRepository;
import com.example.shareit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFountException(commentsDto.getPostId().toString()));
    }
}
