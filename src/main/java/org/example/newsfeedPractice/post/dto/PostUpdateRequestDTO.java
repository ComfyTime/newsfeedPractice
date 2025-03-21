package org.example.newsfeedPractice.post.dto;

import lombok.Getter;

@Getter
public class PostUpdateRequestDTO {

    private Long id;
    private String title;
    private String content;
}
