package outBackend.cloudProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.domain.Comment;
import outBackend.cloudProject.dto.CommentDTO;
import outBackend.cloudProject.dto.CommentRequestDTO;
import outBackend.cloudProject.dto.CommentResponseDTO;
import outBackend.cloudProject.service.commentService.CommentService;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/api/projects/{projectId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long projectId) {
        List<CommentDTO> dtos = commentService.comments(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @PostMapping("/api/projects/{projectId}/comments/{memberId}")
    public ResponseEntity<CommentDTO> create(@PathVariable Long projectId, @PathVariable Long memberId,
                                             @RequestBody CommentDTO dto) {
        CommentDTO createdDto = commentService.create(projectId, memberId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    @PatchMapping("/api/comment/{id}/{memberId}")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @PathVariable Long memberId,
                                             @RequestBody CommentDTO dto) {
        CommentDTO updateDto = commentService.update(id, memberId,dto);
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    @DeleteMapping("/api/comments/{id}/{memberId}")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id, @PathVariable Long memberId) {
        CommentDTO deletedDTO = commentService.delete(id, memberId);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDTO);
    }
}
