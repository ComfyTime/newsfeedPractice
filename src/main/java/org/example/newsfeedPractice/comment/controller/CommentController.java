package org.example.newsfeedPractice.comment.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.comment.dto.CommentSaveRequestDTO;
import org.example.newsfeedPractice.comment.dto.CommentSaveResponseDTO;
import org.example.newsfeedPractice.comment.entity.Comment;
import org.example.newsfeedPractice.comment.service.CommentService;
import org.example.newsfeedPractice.common.consts.Const;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments/{postId}")
    public ResponseEntity<CommentSaveResponseDTO> saveComment(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long postId,
            @RequestBody CommentSaveRequestDTO commentSaveRequestDTO) {
        return ResponseEntity.ok(commentService.saveComment(memberId, postId, commentSaveRequestDTO));
    }

    // 게시글의 댓글 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentSaveResponseDTO>> listComments(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.listComments(postId));
    }


    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentSaveResponseDTO> updateComment(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long commentId,
            @RequestBody CommentSaveRequestDTO requestDTO) {
        return ResponseEntity.ok(commentService.updateComment(memberId, commentId, requestDTO.getContent()));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long commentId) {
        commentService.deleteComment(memberId, commentId);
        return ResponseEntity.noContent().build();
    }

    // 댓글 좋아요
    @PostMapping("/comments/{commentId}/like")
    public ResponseEntity<Void> likeComment(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long commentId) {
        commentService.likeComment(memberId, commentId);
        return ResponseEntity.ok().build();
    }

    //좋아요 취소
    @PostMapping("/comments/{commentId}/unlike")
    public ResponseEntity<Void> unlikeComment(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long commentId) {
        commentService.unlikeComment(memberId, commentId);
        return ResponseEntity.ok().build();
    }

}
