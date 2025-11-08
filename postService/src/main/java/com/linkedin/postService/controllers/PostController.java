package com.linkedin.postService.controllers;

import com.linkedin.postService.dto.PostCreateRequestDTO;
import com.linkedin.postService.dto.PostDTO;
import com.linkedin.postService.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostCreateRequestDTO postDTO, HttpServletResponse httpServletResponse) {
        PostDTO createdPost = postService.createPost(postDTO, 1L);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}
