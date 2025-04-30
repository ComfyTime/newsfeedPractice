package org.example.newsfeedPractice.comment.repository;

import org.example.newsfeedPractice.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostIdAndDeletedAtIsNull(Long postId);
}
