package org.example.newsfeedPractice.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class CommentLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id", nullable = false)
    private Comment comment;

    private Long memberId; // 좋아요 누른 사람

    public CommentLike(Comment comment, Long memberId) {
        this.comment = comment;
        this.memberId = memberId;
    }
}
