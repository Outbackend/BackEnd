package outBackend.cloudProject.service.commentService;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import outBackend.cloudProject.domain.Member;
import outBackend.cloudProject.domain.Project;
import outBackend.cloudProject.dto.CommentDto;
import outBackend.cloudProject.entity.Comment;
import outBackend.cloudProject.repository.CommentRepository;
import outBackend.cloudProject.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
//    //프로젝트 레파지토리 생성시 의존성 주입
//    @Autowired
//    private ProjectRepository projectRepository;
    @Autowired
    private MemberRepository memberRepository;

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


    public List<CommentDto> comments(Long projectId) {
        return commentRepository.findByMemberId(projectId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
}
