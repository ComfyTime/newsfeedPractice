package org.example.newsfeedPractice.comment.dto;

import lombok.Getter;

@Getter
public class CommentUpdateRequestDTO {

    private Long id;
    private Long postId;
    private String content;

}
