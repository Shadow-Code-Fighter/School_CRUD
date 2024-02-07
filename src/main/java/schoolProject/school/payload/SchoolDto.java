package schoolProject.school.payload;

import lombok.Data;

@Data
public class SchoolDto {
    private long id;
    private String classRoom;
    private String section;
    private String description;
    private long students;
}
