package org.example.newsfeedPractice.post.repository;

import org.example.newsfeedPractice.post.entity.Post;
import org.example.newsfeedPractice.post.entity.PostLike;
import org.example.newsfeedPractice.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByMemberAndPost(Member member, Post post);
    long countByPost(Post post);
}
