package com.linkedin.postService.services.impl;

import com.linkedin.postService.dto.PostCreateRequestDTO;
import com.linkedin.postService.dto.PostDTO;
import com.linkedin.postService.entities.Post;
import com.linkedin.postService.exceptions.ResourceNotFoundException;
import com.linkedin.postService.repositories.PostRepository;
import com.linkedin.postService.services.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO createPost(PostCreateRequestDTO postDTO, Long userId) {
        Post post = modelMapper.map(postDTO, Post.class);
        post.setUserId(userId);

        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPost(Long postId) {
        log.debug("Getting post with Id : {}", postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : "+postId));
        return modelMapper.map(post, PostDTO.class);
    }
}
