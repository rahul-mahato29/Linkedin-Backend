package com.linkedin.postService.services.impl;

import com.linkedin.postService.entities.PostLike;
import com.linkedin.postService.exceptions.BadRequestException;
import com.linkedin.postService.exceptions.ResourceNotFoundException;
import com.linkedin.postService.repositories.PostLikeRepository;
import com.linkedin.postService.repositories.PostRepository;
import com.linkedin.postService.services.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;

    @Override
    public void likePost(Long postId, Long userId) {
        log.info("Like post with id : {}", postId);
        boolean postExists = postRepository.existsById(postId);
        if(!postExists) throw new ResourceNotFoundException("Post Not Found With Id : "+postId);

        boolean alreadyLiked = postLikeRepository.existsByUserIdAndPostId(postId, userId);
        if(alreadyLiked) throw new BadRequestException("Cannot like the same post again");

        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);
    }
}
