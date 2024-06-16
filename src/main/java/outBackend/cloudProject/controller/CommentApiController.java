package outBackend.cloudProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import outBackend.cloudProject.dto.CommentDto;
import outBackend.cloudProject.service.commentService.CommentService;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/api/project/{projectId}/comment")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long projectId) {
        List<CommentDto> dtos = commentService.comments(projectId);
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

//    @PostMapping("/api/project/{projectId}/comment")
//    public ResponseEntity<CommentDto> create(@PathVariable Long projectId,
//                                             @RequestBody CommentDto dto) {
//        CommentDto createdDto = commentService.create(projectId, dto);
//        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
//    }
//
//    @PatchMapping("/api/comment/{id}")
//    public ResponseEntity<CommentDto> update(@PathVariable Long id,
//                                             @RequestBody CommentDto dto) {
//        CommentDto updateDto = commentService.update(id, dto);
//        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
//    }
}
