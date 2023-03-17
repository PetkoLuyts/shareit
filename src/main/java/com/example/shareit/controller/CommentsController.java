package com.example.shareit.controller;

import com.example.shareit.dto.CommentsDto;
import com.example.shareit.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentsController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Object> createComment(@RequestBody CommentsDto commentsDto) {
        commentService.save(commentsDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
