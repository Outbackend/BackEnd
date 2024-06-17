package outBackend.cloudProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import outBackend.cloudProject.apiPayload.ApiResponse;
import outBackend.cloudProject.domain.Comment;
import outBackend.cloudProject.dto.CommentRequestDTO;
import outBackend.cloudProject.dto.CommentResponseDTO;
import outBackend.cloudProject.service.commentService.CommentService;
import outBackend.cloudProject.converter.CommentConverter;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/api/projects/{projectId}/comment")
    public ApiResponse<CommentResponseDTO.SaveResultDTO> addComment(@RequestHeader("accessToken")String accessToken, @RequestBody CommentRequestDTO.SaveDTO request) {
        Comment comment = commentService.save(accessToken, request);
        return ApiResponse.onSuccess(CommentConverter.toaddResultDTO(comment));
    }





//
//    @GetMapping("/api/project/{projectId}/comment")
//    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long projectId) {
//        List<CommentDto> dtos = commentService.comments(projectId);
//        return ResponseEntity.status(HttpStatus.OK).body(dtos);
//    }

    // commit test
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
