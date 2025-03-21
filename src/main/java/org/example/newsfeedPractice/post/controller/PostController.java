package org.example.newsfeedPractice.post.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.common.consts.Const;
import org.example.newsfeedPractice.post.dto.*;
import org.example.newsfeedPractice.post.service.PostService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostSaveResponseDTO> savePost(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody PostSaveRequestDTO postSaveRequestDto
    ) {
        return ResponseEntity.ok(postService.savePost(memberId, postSaveRequestDto));
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.findPost(postId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDTO>> getPostsByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
    ) {
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(endDate, LocalTime.MAX);
        
        List<PostResponseDTO> posts = postService.getPostsByDate(startDateTime, endDateTime);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/newspeed")
    public ResponseEntity<List<PostResponseDTO>> getFollowingsPosts(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId
    ) {
        List<PostResponseDTO> posts = postService.getFollowingsPosts(memberId);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostUpdateResponseDTO> updatePost(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long postId,
            @RequestBody PostUpdateRequestDTO postUpdateRequestDto
    ) {
        return ResponseEntity.ok(postService.updatePost(memberId, postId, postUpdateRequestDto));
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<Void> deletePost(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long postId
    ) {
        postService.deletePost(memberId, postId);
        return ResponseEntity.ok().build();
    }
}