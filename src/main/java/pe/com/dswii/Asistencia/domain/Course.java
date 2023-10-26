package pe.com.dswii.Asistencia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private Integer courseId;
    private String courseName;
    private String courseCredits;
    private int careerId;
    private String careerActive;

    private Career objCareer;
}
