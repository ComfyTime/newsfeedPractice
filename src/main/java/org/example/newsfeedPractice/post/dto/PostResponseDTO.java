package org.example.newsfeedPractice.post.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDTO {

    private final Long id;
    private final String title;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostResponseDTO(Long id, String title, String content, String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
