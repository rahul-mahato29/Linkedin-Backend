package com.linkedin.postService.services;

import com.linkedin.postService.dto.PostCreateRequestDTO;
import com.linkedin.postService.dto.PostDTO;

public interface PostService {
    PostDTO createPost(PostCreateRequestDTO postDTO, Long userId);

    PostDTO getPost(Long postId);
}
