package com.linkedin.postService.services;

import com.linkedin.postService.dto.PostCreateRequestDTO;
import com.linkedin.postService.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostCreateRequestDTO postDTO, Long userId);

    PostDTO getPost(Long postId);

    List<PostDTO> getAllPostsOfUser(Long userId);
}
