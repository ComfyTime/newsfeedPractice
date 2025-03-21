package org.example.newsfeedPractice.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostSaveResponseDTO {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;

    public PostSaveResponseDTO(Long id, String title, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }
}
