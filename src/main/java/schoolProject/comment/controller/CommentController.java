package schoolProject.comment.controller;

import schoolProject.comment.payload.CommentDto;
import schoolProject.comment.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        this.commentService=commentService;
    }

//    http://localhost:8080/api/comment?postId=2
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){
        CommentDto dto = commentService.createComment(commentDto,postId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

//    http://localhost:8080/api/comment/2
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
       commentService.deleteComment(id);
       return new ResponseEntity<>("Comment is deleted!!!",HttpStatus.OK);
    }

//    http://localhost:8080/api/comment/1
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto){
       CommentDto dto = commentService.updateComment(id,commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }

}
