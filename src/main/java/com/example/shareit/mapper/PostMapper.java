package com.example.shareit.mapper;

import com.example.shareit.dto.PostRequest;
import com.example.shareit.dto.PostResponse;
import com.example.shareit.model.Post;
import com.example.shareit.model.Subreddit;
import com.example.shareit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);
}
