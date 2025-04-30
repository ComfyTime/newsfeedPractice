package org.example.newsfeedPractice.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.comment.dto.CommentSaveRequestDTO;
import org.example.newsfeedPractice.comment.dto.CommentSaveResponseDTO;
import org.example.newsfeedPractice.comment.entity.Comment;
import org.example.newsfeedPractice.comment.entity.CommentLike;
import org.example.newsfeedPractice.comment.repository.CommentLikeRepository;
import org.example.newsfeedPractice.comment.repository.CommentRepository;
import org.example.newsfeedPractice.member.entity.Member;
import org.example.newsfeedPractice.member.repository.MemberRepository;
import org.example.newsfeedPractice.post.entity.Post;
import org.example.newsfeedPractice.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentLikeRepository commentLikeRepository;

    public CommentSaveResponseDTO saveComment(Long memberId, Long postId, CommentSaveRequestDTO commentSaveRequestDTO) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("해당 회원 없음"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("해당 게시글 없음"));

        Comment comment = new Comment(
                commentSaveRequestDTO.getContent(),
                member.getId(),  // authorId 저장
                member.getName() // author 저장
        );
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);

        return new CommentSaveResponseDTO(
                savedComment.getId(),
                savedComment.getPost().getId(),
                savedComment.getContent(),
                savedComment.getAuthor(),
                savedComment.getCreatedAt()
        );
    }

    public List<CommentSaveResponseDTO> listComments(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostIdAndDeletedAtIsNull(postId);

        return comments.stream()
                .map(c -> new CommentSaveResponseDTO(
                        c.getId(),
                        c.getPost().getId(),
                        c.getContent(),
                        c.getAuthor(),
                        c.getCreatedAt()
                ))
                .toList();
    }



    public CommentSaveResponseDTO updateComment(Long memberId, Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("댓글을 찾을 수 없습니다."));

        if (!comment.getAuthorId().equals(memberId)) { // authorId로 체크
            throw new IllegalStateException("댓글을 수정할 권한이 없습니다.");
        }

        comment.updateComment(content);

        return new CommentSaveResponseDTO(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getAuthor(),
                comment.getCreatedAt()
        );
    }


    public void deleteComment(Long memberId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("댓글을 찾을 수 없습니다."));

        if (!comment.getAuthorId().equals(memberId)) {
            throw new IllegalStateException("댓글을 삭제할 권한이 없습니다.");
        }

        comment.delete();
    }// Soft Delete 처리

    // 댓글 좋아요
    public void likeComment(Long memberId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("댓글을 찾을 수 없습니다."));

        boolean alreadyLiked = commentLikeRepository.findByCommentIdAndMemberId(commentId, memberId).isPresent();
        if (alreadyLiked) {
            throw new IllegalStateException("이미 좋아요를 눌렀습니다.");
        }

        CommentLike commentLike = new CommentLike(comment, memberId);
        commentLikeRepository.save(commentLike);

        comment.likeComment(); // 좋아요 수 증가
    }

    // 댓글 좋아요 취소
    public void unlikeComment(Long memberId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("댓글을 찾을 수 없습니다."));

        CommentLike commentLike = commentLikeRepository.findByCommentIdAndMemberId(commentId, memberId)
                .orElseThrow(() -> new IllegalStateException("좋아요를 누른 기록이 없습니다."));

        commentLikeRepository.delete(commentLike); // 좋아요 기록 삭제

        comment.unlikeComment(); // 좋아요 수 감소
    }

}


