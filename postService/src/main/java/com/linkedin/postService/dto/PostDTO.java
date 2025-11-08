package com.linkedin.postService.dto;

import lombok.Data;

@Data
public class PostDTO {

    private Long id;
    private Long userId;
    private String description;
    private String[] photos;
}
