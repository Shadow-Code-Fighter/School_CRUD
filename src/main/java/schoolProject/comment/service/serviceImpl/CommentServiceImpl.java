package schoolProject.comment.service.serviceImpl;

import schoolProject.comment.entity.Comment;
import schoolProject.comment.payload.CommentDto;
import schoolProject.comment.repository.CommentRepo;
import schoolProject.comment.service.CommentService;
import schoolProject.school.entity.School;
import schoolProject.school.exception.ResourceNotFoundException;
import schoolProject.school.repository.SchoolRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepo commentRepo;
    private SchoolRepo schoolRepo;
    private ModelMapper modelMapper;
    public CommentServiceImpl(CommentRepo commentRepo,ModelMapper modelMapper,SchoolRepo schoolRepo ){
        this.commentRepo=commentRepo;
        this.modelMapper=modelMapper;
        this.schoolRepo=schoolRepo;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        School school = schoolRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("School Not fount with id: " + postId));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());
        comment.setSchool(school);
        Comment saveComment = commentRepo.save(comment);
        CommentDto dto = modelMapper.map(saveComment, CommentDto.class);
        return dto;
    }

    @Override
    public void deleteComment(long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto) {
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment is Not Found woth id: " + id));
        comment.setText(commentDto.getText());
        comment.setEmail(commentDto.getEmail());
        Comment saveComment = commentRepo.save(comment);
        CommentDto dto = modelMapper.map(saveComment, CommentDto.class);
        return dto;
    }
}
