package com.example.shareit.service;

import com.example.shareit.dto.PostRequest;
import com.example.shareit.dto.PostResponse;
import com.example.shareit.exceptions.PostNotFountException;
import com.example.shareit.exceptions.SubredditNotFoundException;
import com.example.shareit.mapper.PostMapper;
import com.example.shareit.model.Post;
import com.example.shareit.model.Subreddit;
import com.example.shareit.model.User;
import com.example.shareit.repository.PostRepository;
import com.example.shareit.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();

        return postMapper.map(postRequest, subreddit, currentUser);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
         return postRepository.findAll()
                 .stream()
                 .map(postMapper::mapToDto)
                 .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFountException(id.toString()));

        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);

        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }
}
