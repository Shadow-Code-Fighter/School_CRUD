package schoolProject.comment.service;

import schoolProject.comment.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteComment(long id);

    CommentDto updateComment(long id, CommentDto commentDto);
}
