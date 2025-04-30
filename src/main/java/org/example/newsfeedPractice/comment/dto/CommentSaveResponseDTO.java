package org.example.newsfeedPractice.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentSaveResponseDTO {

    private final Long id;
    private final Long postId;
    private final String content;
    private final String author;
    private final LocalDateTime createdAt;

    public CommentSaveResponseDTO(Long id, Long postId, String content, String author, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
    }

}
