package org.example.newsfeedPractice.post.repository;

import org.example.newsfeedPractice.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMemberIdIn(Collection<Long> memberIds);

    List<Post> findAllByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}
