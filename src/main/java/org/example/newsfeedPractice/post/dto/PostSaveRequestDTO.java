package org.example.newsfeedPractice.post.dto;

import lombok.Getter;

@Getter
public class PostSaveRequestDTO {

    private Long id;
    private String title;
    private String content;
}
