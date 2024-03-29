package schoolProject.comment.entity;


import schoolProject.school.entity.School;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text;
    private String email;

    //    many comments can belong to one post
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
}
