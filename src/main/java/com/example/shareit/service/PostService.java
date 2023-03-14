package com.example.shareit.service;

import com.example.shareit.dto.PostRequest;
import com.example.shareit.exceptions.SubredditNotFoundException;
import com.example.shareit.mapper.PostMapper;
import com.example.shareit.model.Post;
import com.example.shareit.model.Subreddit;
import com.example.shareit.model.User;
import com.example.shareit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();

        return postMapper.map(postRequest, subreddit, currentUser);
    }
}
