package outBackend.cloudProject.service.commentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.converter.ProjectConverter;
import outBackend.cloudProject.domain.Comment;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.domain.mapping.MemberProject;
import outBackend.cloudProject.dto.CommentDTO;
import outBackend.cloudProject.dto.CommentRequestDTO;
import outBackend.cloudProject.repository.CommentRepository;
import outBackend.cloudProject.repository.MemberRepository;
import outBackend.cloudProject.repository.ProjectRepository;
import outBackend.cloudProject.security.TokenProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final ProjectRepository projectRepository;

    public List<CommentDTO> comments(Long projectId) {
        return commentRepository.findByProjectId(projectId)
                .stream()
                .map(comment -> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDTO create(Long projectId, Long memberId, CommentDTO dto) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                        "대상 게시글이 없습니다."));
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
                        "회원이 아닙니다."));

        Comment comment = Comment.createComment(dto, project, member);
        Comment created = commentRepository.save(comment);
        return CommentDTO.createCommentDTO((created));
    }

    @Transactional
    public CommentDTO update(Long id, Long memberId, CommentDTO dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" +
                        "대상 댓글이 없습니다."));

        target.patch(dto, memberId);
        Comment updated = commentRepository.save(target);
        return CommentDTO.createCommentDTO(updated);
    }


    public CommentDTO delete(Long id, Long memberId) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! " +
                        "대상이 없습니다."));
        if (target.getMember().getId() != memberId) {
            throw new IllegalArgumentException("댓글 삭제 실패! 이 댓글 작성자가 아닙니다.");
        }
        commentRepository.delete(target);
        return CommentDTO.createCommentDTO((target));
    }
}
