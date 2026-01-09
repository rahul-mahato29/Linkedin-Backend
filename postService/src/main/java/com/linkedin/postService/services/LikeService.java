package com.linkedin.postService.services;

import org.springframework.stereotype.Service;


public interface LikeService {
    void likePost(Long id, Long userId);

    void unLikePost(Long postId, long userId);
}
