package org.example.newsfeedPractice.post.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.follow.entity.Follow;
import org.example.newsfeedPractice.follow.repository.FollowRepository;
import org.example.newsfeedPractice.member.entity.Member;
import org.example.newsfeedPractice.member.repository.MemberRepository;
import org.example.newsfeedPractice.post.dto.*;
import org.example.newsfeedPractice.post.entity.Post;
import org.example.newsfeedPractice.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FollowRepository followRepository;

    @Transactional
    public PostSaveResponseDTO savePost(Long memberId, PostSaveRequestDTO postSaveRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("해당 회원 없음"));

        Post post = new Post(
                postSaveRequestDto.getTitle(),
                postSaveRequestDto.getContent(),
                member
        );

        Post savedPost = postRepository.save(post);

        return new PostSaveResponseDTO(
                savedPost.getId(),
                savedPost.getTitle(),
                savedPost.getContent(),
                savedPost.getAuthor(),
                savedPost.getCreatedAt()
        );
    }

    @Transactional(readOnly = true)
    public PostResponseDTO findPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글 없음"));
        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByDate(LocalDateTime startDate, LocalDateTime endDate) {
        List<Post> posts = postRepository.findAllByCreatedAtBetween(startDate, endDate);
        return posts.stream()
                .map(news -> new PostResponseDTO(
                        news.getId(),
                        news.getTitle(),
                        news.getContent(),
                        news.getAuthor(),
                        news.getCreatedAt(),
                        news.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDTO> getFollowingsPosts(Long followerId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new IllegalStateException("해당 회원 없음"));

        List<Follow> followings = followRepository.findByFollowerId(followerId);

        List<Long> followingMemberIds = followings.stream()
                .map(Follow::getFollowing)
                .map(Member::getId)
                .collect(Collectors.toList());

        List<Post> followingPosts = postRepository.findByMemberIdIn(followingMemberIds);

        return followingPosts.stream()
                .map(post -> new PostResponseDTO(
                        post.getId(),
                        post.getTitle(),
                        post.getContent(),
                        post.getAuthor(),
                        post.getCreatedAt(),
                        post.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public PostUpdateResponseDTO updatePost(Long memberId, Long postId, PostUpdateRequestDTO postUpdateRequestDto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("해당 회원 없음"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글 없음"));

        if (!memberId.equals(post.getMember().getId())) {
            throw new IllegalStateException("작성자 아님");
        }

        post.setTitle(postUpdateRequestDto.getTitle());
        post.setContent(postUpdateRequestDto.getContent());

        Post updatedPost = postRepository.save(post);

        return new PostUpdateResponseDTO(
                updatedPost.getId(),
                updatedPost.getTitle(),
                updatedPost.getContent(),
                updatedPost.getAuthor(),
                updatedPost.getCreatedAt(),
                updatedPost.getUpdatedAt()
        );
    }

    public void deletePost(Long memberId, Long postId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("해당 회원 없음"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글 없음"));

        if (!memberId.equals(post.getMember().getId())) {
            throw new IllegalStateException("작성자 아님");
        }

        postRepository.delete(post);
    }
}
