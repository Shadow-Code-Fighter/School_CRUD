package schoolProject.school.entity;



import schoolProject.comment.entity.Comment;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String classRoom;
    private String section;
    private String description;
    private long students;


    @OneToMany(mappedBy = "school",cascade = CascadeType.ALL)
    private List<Comment> comments;
}
