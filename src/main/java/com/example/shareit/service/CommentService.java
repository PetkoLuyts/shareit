package com.example.shareit.service;

import com.example.shareit.dto.CommentsDto;
import com.example.shareit.exceptions.PostNotFountException;
import com.example.shareit.mapper.CommentMapper;
import com.example.shareit.model.Comment;
import com.example.shareit.model.NotificationEmail;
import com.example.shareit.model.Post;
import com.example.shareit.model.User;
import com.example.shareit.repository.CommentRepository;
import com.example.shareit.repository.PostRepository;
import com.example.shareit.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {
    private static final String POST_URL = "";
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFountException(commentsDto.getPostId().toString()));

        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(post.getUser().getUsername() +
                "commented on your post " + POST_URL);

        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + "Commented on your post",
                user.getEmail(), message));
    }
}
