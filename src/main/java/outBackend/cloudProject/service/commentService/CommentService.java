package outBackend.cloudProject.service.commentService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.domain.Comment;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.dto.CommentRequestDTO;
import outBackend.cloudProject.repository.CommentRepository;
import outBackend.cloudProject.repository.MemberRepository;
import outBackend.cloudProject.converter.CommentConverter;
import outBackend.cloudProject.security.TokenProvider;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public Comment save(String accessToken, CommentRequestDTO.SaveDTO request) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        Member member = memberRepository.findByEmail(authentication.getName()).get();

        Comment comment = CommentConverter.toComment(request);
        return commentRepository.save(comment);
    }

//    @Transactional
//    public CommentDto update(Long id, CommentDto dto) {
//        Comment target = commentRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" +
//                        "대상 댓글이 없습니다."));
//        target.patch(dto);
//        Comment updated = commentRepository.save(target);
//        return CommentDto.createCommentDto(updated);
//    }

//    @Transactional
//    public CommentDto create(Long projectId, CommentDto dto) {
//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! " +
//                        "대상 게시글이 없습니다."));
//        Comment comment = Comment.createComment(dto, project);
//        Comment created = commentRepository.save(comment);
//        return CommentDto.createCommentDto(created);
//    }


}
